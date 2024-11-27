package com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response

data class Beach(
    val id: String,
    val name: String,
    val latitude: String,
    val longitude: String,
    val region: String,
    val openingTime: String,
    val closingTime: String,
    val activities: List<String>,
    val photos: List<String>
)
