package com.singhtwenty2.oceanvista.feature_home.data.remote.model.dto.response

data class ProfileResponseDTO(
    val user_id: String,
    val username: String,
    val name: String?,
    val phoneNumber: String?,
    val email: String,
    val notificationPrefrence: String
)
