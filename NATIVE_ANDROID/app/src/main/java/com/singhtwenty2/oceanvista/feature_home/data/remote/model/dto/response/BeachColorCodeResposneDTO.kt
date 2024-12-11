package com.singhtwenty2.oceanvista.feature_home.data.remote.model.dto.response

data class BeachColorCodeResposneDTO(
    val features: List<BeachWeatherFeatureDto>
)

data class BeachWeatherFeatureDto(
    val geometry: GeometryDto,
    val properties: PropertiesDto,
    val type: String
)

data class GeometryDto(
    val coordinates: List<Double>,
    val type: String
)

data class PropertiesDto(
    val color_code: String,
    val condition: String,
    val humidity: Int,
    val id: Int,
    val last_updated: String,
    val name: String,
    val region: String,
    val temperature_c: Double,
    val wind_kph: Double
)