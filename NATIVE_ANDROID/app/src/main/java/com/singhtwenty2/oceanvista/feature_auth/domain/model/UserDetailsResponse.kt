package com.singhtwenty2.oceanvista.feature_auth.domain.model

data class UserDetailsResponse(
    val userName: String,
    val email: String,
    val notificationPrefrence: String
)