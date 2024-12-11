package com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response

data class BeachColorCode(
    val id: Int,
    val name: String,
    val region: String,
    val latitude: Double,
    val longitude: Double,
    val temperature: Double,
    val humidity: Int,
    val windSpeed: Double,
    val condition: String,
    val conditionColor: ConditionColor,
    val lastUpdated: String
)

enum class ConditionColor {
    GREEN, YELLOW, ORANGE, RED, GRAY
}

data class Coordinates(
    val latitude: Double,
    val longitude: Double
)