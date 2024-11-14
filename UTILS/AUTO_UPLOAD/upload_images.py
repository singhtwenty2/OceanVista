import os
import re
import firebase_admin
from firebase_admin import credentials, storage

# Step 1: Initialize Firebase Admin SDK with storageBucket
cred = credentials.Certificate("/Users/singhtwenty2/Downloads/imessagebackend-firebase-adminsdk-jz3bg-78693842db.json")
firebase_admin.initialize_app(cred, {
    'storageBucket': 'imessagebackend.appspot.com'  # Replace with your Firebase project ID
})

bucket = storage.bucket()  # This will now work because storageBucket is set during initialization

# Step 2: Directory containing beach photos
image_dir = "/Users/singhtwenty2/Developer/Projects/OceanVista/UTILS/AUTO_UPLOAD/Beach-Photos"

# Regex pattern to match beach name and image number (e.g., 'Anjuna Beach1.jpg', 'Anjuna Beach2.jpeg')
pattern = re.compile(r'([a-zA-Z\s]+)(\d+)\.(jpeg|jpg|webp|png|avif)', re.IGNORECASE)

# Dictionary to hold beach image URLs
beach_urls = {}

# Step 3: Upload beach images and categorize by beach
for filename in os.listdir(image_dir):
    match = pattern.match(filename)
    if match:
        beach_name = match.group(1).strip().lower()  # Extract beach name, make it lowercase for consistency
        image_number = int(match.group(2))  # Extract the image number
        
        # Upload the image to Firebase Storage
        blob = bucket.blob(f"beaches/{filename}")
        blob.upload_from_filename(os.path.join(image_dir, filename))
        blob.make_public()
        image_url = blob.public_url
        
        # Initialize the beach entry if it doesn't exist
        if beach_name not in beach_urls:
            beach_urls[beach_name] = {'cover': None, 'catalog': []}
        
        # Step 4: Categorize the image as cover or catalog
        if image_number == 1:
            beach_urls[beach_name]['cover'] = image_url
        else:
            beach_urls[beach_name]['catalog'].append(image_url)

# Step 5: Save URLs to a formatted file
with open("beach_image_urls.txt", "w") as file:
    for beach_name, urls in beach_urls.items():
        file.write(f"[{beach_name.capitalize()} Beach]\n")
        file.write(f"Cover Photo URL = {urls['cover']}\n")
        for index, catalog_url in enumerate(urls['catalog']):
            file.write(f"Catalog {index + 1} URL = {catalog_url}\n")
        file.write("\n")

print("Beach image upload and URL generation complete. URLs saved to 'beach_image_urls.txt'.")