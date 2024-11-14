package com.singhtwenty2.oceanvista.feature_auth.domain.model

data class RegisterRequest(
    val email: String,
    val password: String,
    val notificationPref: String,
)
