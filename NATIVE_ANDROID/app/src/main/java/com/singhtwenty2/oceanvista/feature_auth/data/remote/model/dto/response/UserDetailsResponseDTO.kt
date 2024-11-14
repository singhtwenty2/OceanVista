package com.singhtwenty2.oceanvista.feature_auth.data.remote.model.dto.response

data class UserDetailsResponseDTO(
    val userName: String,
    val email: String,
    val notificationPrefrence: String
)