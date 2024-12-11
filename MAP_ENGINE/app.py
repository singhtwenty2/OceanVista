from flask import Flask, jsonify
import requests
import redis
import json
import threading
import time

app = Flask(__name__)

# Redis connection
redis_client = redis.Redis(host='localhost', port=7040, decode_responses=True)



# Beach data
BEACHES = [
    {"id": 1, "name": "Calangute Beach", "region": "Goa", "coordinates": {"latitude": 15.5439, "longitude": 73.7553}},
    {"id": 2, "name": "Baga Beach", "region": "Goa", "coordinates": {"latitude": 15.5523, "longitude": 73.7517}},
    {"id": 3, "name": "Palolem Beach", "region": "Goa", "coordinates": {"latitude": 15.0100, "longitude": 74.0230}},
    {"id": 4, "name": "Anjuna Beach", "region": "Goa", "coordinates": {"latitude": 15.5783, "longitude": 73.7437}},
    {"id": 5, "name": "Vagator Beach", "region": "Goa", "coordinates": {"latitude": 15.5994, "longitude": 73.7369}},
    {"id": 6, "name": "Kaup Beach", "region": "Mangaluru", "coordinates": {"latitude": 13.2120, "longitude": 74.7480}},
    {"id": 7, "name": "Radhanagar Beach", "region": "Andaman and Nicobar Islands", "coordinates": {"latitude": 11.9796, "longitude": 92.9876}},
    {"id": 8, "name": "Kovalam Beach", "region": "Kerala", "coordinates": {"latitude": 8.4006, "longitude": 76.9784}},
    {"id": 9, "name": "Cherai Beach", "region": "Kerala", "coordinates": {"latitude": 10.1015, "longitude": 76.1926}},
    {"id": 10, "name": "Om Beach", "region": "Karnataka", "coordinates": {"latitude": 14.5155, "longitude": 74.3112}},
    {"id": 11, "name": "Gokarna Beach", "region": "Karnataka", "coordinates": {"latitude": 14.5500, "longitude": 74.3180}},
    {"id": 12, "name": "Puri Beach", "region": "Bhubaneswar", "coordinates": {"latitude": 19.7983, "longitude": 85.8249}},
    {"id": 13, "name": "Dhanushkodi Beach", "region": "Tamil Nadu", "coordinates": {"latitude": 9.2305, "longitude": 79.3392}},
    {"id": 14, "name": "Elephant Beach", "region": "Andaman", "coordinates": {"latitude": 12.0016, "longitude": 93.0182}},
    {"id": 15, "name": "Juhu Beach", "region": "Mumbai, Maharashtra", "coordinates": {"latitude": 19.0974, "longitude": 72.8266}},
    {"id": 16, "name": "Mahabalipuram Beach", "region": "Tamil Nadu", "coordinates": {"latitude": 12.6208, "longitude": 80.1918}},
    {"id": 17, "name": "Auroville Beach", "region": "Pondicherry", "coordinates": {"latitude": 12.0123, "longitude": 79.8577}},
    {"id": 18, "name": "Nagoa Beach", "region": "Diu", "coordinates": {"latitude": 20.7013, "longitude": 70.8906}},
    {"id": 19, "name": "Rishikonda Beach", "region": "Visakhapatnam", "coordinates": {"latitude": 17.7726, "longitude": 83.3770}},
    {"id": 20, "name": "Minicoy Island Beach", "region": "Lakshadweep", "coordinates": {"latitude": 8.2842, "longitude": 73.0489}}
]

def map_to_color_code(condition_text, temp, wind, humidity, cloud, uv, precip, feels_like):
    """
    Determine color code based on multiple weather parameters
    with a more nuanced and robust mapping approach.
    
    Args:
        condition_text (str): Current weather condition
        temp (float): Temperature in Celsius
        wind (float): Wind speed in kph
        humidity (int): Humidity percentage
        cloud (int): Cloud cover percentage
        uv (float): UV index
        precip (float): Precipitation in mm
        feels_like (float): Feels like temperature
    
    Returns:
        str: Color code representing overall weather conditions
    """
    # Base color determination logic
    def get_base_color():
        # Extreme conditions
        if temp > 35 or feels_like > 40:
            return "red"  # Extremely hot
        
        if temp < 10:
            return "violet"  # Extremely cold
        
        # Wind conditions
        if wind > 35:
            return "red"  # Very strong winds
        
        if wind > 25:
            return "orange"  # Strong winds
        
        # Precipitation and cloud conditions
        if precip > 10 or cloud > 80:
            return "violet"  # Heavy rain or very cloudy
        
        if precip > 1 or cloud > 60:
            return "orange"  # Moderate rain or significant cloud cover
        
        # UV and heat index
        if uv > 8:
            return "red"  # High UV radiation
        
        if uv > 6:
            return "orange"  # Moderate to high UV
        
        # Humidity and comfort
        if humidity > 85:
            return "violet"  # Very humid and uncomfortable
        
        if humidity < 30:
            return "yellow"  # Very dry conditions
        
        # Condition-based mapping with more nuance
        condition_mapping = {
            "Clear": "yellow",
            "Sunny": "yellow",
            "Partly Cloudy": "orange",
            "Cloudy": "orange",
            "Overcast": "violet",
            "Mist": "violet",
            "Patchy rain possible": "orange",
            "Rain": "orange",
            "Light Rain": "orange",
            "Thunderstorm": "red",
            "Heavy Rain": "red"
        }
        
        # Check for specific condition
        specific_color = condition_mapping.get(condition_text)
        if specific_color:
            return specific_color
        
        # Default fallback
        return "yellow"
    
    # Final color determination
    return get_base_color()

# Fetch weather data for a single beach
def fetch_weather(beach):
    region = beach["region"]
    url = f"https://api.weatherapi.com/v1/current.json?key={API_KEY}&q={region}&aqi=no"
    response = requests.get(url)
    if response.status_code == 200:
        data = response.json()
        current = data["current"]
        temp = current["temp_c"]
        wind = current["wind_kph"]
        humidity = current["humidity"]
        condition = current["condition"]["text"]
        cloud = current["cloud"]
        uv = current["uv"]
        precip = current["precip_mm"]
        feels_like = current["feelslike_c"]
        
        color_code = map_to_color_code(
            condition_text=condition,
            temp=temp,
            wind=wind,
            humidity=humidity,
            cloud=cloud,
            uv=uv,
            precip=precip,
            feels_like=feels_like
        )
        
        return {
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [beach["coordinates"]["longitude"], beach["coordinates"]["latitude"]]
            },
            "properties": {
                "id": beach["id"],
                "name": beach["name"],
                "region": region,
                "temperature_c": temp,
                "condition": condition,
                "color_code": color_code,
                "humidity": humidity,
                "wind_kph": wind,
                "last_updated": current["last_updated"]
            }
        }
    else:
        return None

# Fetch weather for all beaches and store in Redis
def update_weather_data():
    while True:
        geojson_data = {"type": "FeatureCollection", "features": []}
        for beach in BEACHES:
            weather_data = fetch_weather(beach)
            if weather_data:
                geojson_data["features"].append(weather_data)
        redis_client.set("MAP_ENGINE:beach_weather", json.dumps(geojson_data))
        print("Updated weather data in Redis.")
        time.sleep(600)  # Wait for 10 minutes

# API endpoint to get GeoJSON data
@app.route("/beach_weather", methods=["GET"])
def get_beach_weather():
    geojson_data = redis_client.get("MAP_ENGINE:beach_weather")
    if geojson_data:
        return jsonify(json.loads(geojson_data))
    else:
        return jsonify({"error": "No data available"}), 500

# Start the background thread for periodic updates
def start_background_task():
    thread = threading.Thread(target=update_weather_data)
    thread.daemon = True
    thread.start()

if __name__ == "__main__":
    start_background_task()
    app.run(host="0.0.0.0", port=5000)