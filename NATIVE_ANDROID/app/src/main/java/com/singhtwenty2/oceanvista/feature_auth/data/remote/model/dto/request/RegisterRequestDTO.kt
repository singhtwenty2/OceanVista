package com.singhtwenty2.oceanvista.feature_auth.data.remote.model.dto.request

import com.singhtwenty2.oceanvista.feature_auth.data.remote.model.enums.NotificationPrefrance

data class RegisterRequestDTO(
    val email: String,
    val notificationPreference: NotificationPrefrance,
    val password: String
)
