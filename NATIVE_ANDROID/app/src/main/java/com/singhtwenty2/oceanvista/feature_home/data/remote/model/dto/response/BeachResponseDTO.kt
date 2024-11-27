package com.singhtwenty2.oceanvista.feature_home.data.remote.model.dto.response

data class BeachResponseDTO(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val region: String,
    val openingTime: String,
    val closingTime: String,
    val activities: List<String>,
    val photos: List<String>
)
