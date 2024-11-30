package com.singhtwenty2.oceanvista.feature_home.data.remote.model.dto.response

data class BeachDetailResponseDTO(
    val id: Long,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val region: String,
    val description: String,
    val createdAt: String,
    val updatedAt: String,
    val beachDetail: BeachDetail,
    val photos: List<String>
)


data class BeachDetail(
    val id: Long,
    val createdAt: String,
    val updatedAt: String,
    val activities: List<String>,
    val amenities: List<String>,
    val openingTime: String,
    val closingTime: String,
    val averageCrowdCount: Long,
    val peakTimes: String,
    val facilities: List<String>,
    val services: List<String>,
    val accessibleForDisabled: Boolean,
    val weatherForecast: String,
    val petFriendly: Boolean,
    val foodAndBeverageOptions: List<String>,
    val shadeAvailable: Boolean,
    val parkingInfo: String,
    val surfConditions: String,
    val lifeguardsAvailable: Boolean,
    val familyFriendly: Boolean,
    val wifiAvailable: Boolean,
    val beachType: String,
    val rulesAndRegulations: List<String>,
    val emergencyContactInfo: String
)
