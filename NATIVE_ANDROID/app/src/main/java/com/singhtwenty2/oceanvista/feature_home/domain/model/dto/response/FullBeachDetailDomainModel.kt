package com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response

data class FullBeachDetailDomainModel(
    val id: Long,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val region: String,
    val description: String,
    val beachDetail: BeachDetailDomainModel,
    val photos: List<String>
)

data class BeachDetailDomainModel(
    val activities: List<String>,
    val amenities: List<String>,
    val openingTime: String,
    val closingTime: String,
    val averageCrowdCount: String,
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
