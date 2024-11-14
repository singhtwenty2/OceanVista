package com.singhtwenty2.oceanvista.feature_auth.data.mapper

import com.singhtwenty2.oceanvista.feature_auth.data.remote.model.dto.request.CheckEmailRequestDTO
import com.singhtwenty2.oceanvista.feature_auth.data.remote.model.dto.request.LoginRequestDTO
import com.singhtwenty2.oceanvista.feature_auth.data.remote.model.dto.request.RegisterRequestDTO
import com.singhtwenty2.oceanvista.feature_auth.data.remote.model.enums.NotificationPrefrance
import com.singhtwenty2.oceanvista.feature_auth.domain.model.CheckEmailRequest
import com.singhtwenty2.oceanvista.feature_auth.domain.model.LoginRequest
import com.singhtwenty2.oceanvista.feature_auth.domain.model.RegisterRequest

fun CheckEmailRequest.toCheckEmailRequestDTO(): CheckEmailRequestDTO {
    return CheckEmailRequestDTO(
        email = email
    )
}

fun LoginRequest.toLoginRequestDTO(): LoginRequestDTO {
    return LoginRequestDTO(
        email = email,
        password = password
    )
}

fun RegisterRequest.toRegisterRequestDTO(): RegisterRequestDTO {
    return RegisterRequestDTO(
        email = email,
        password = password,
        notificationPreference = notificationPref.toNotificationPrefrence()
    )
}

fun String.toNotificationPrefrence(): NotificationPrefrance {
    return when (this) {
        "Email" -> NotificationPrefrance.EMAIL
        "In App" -> NotificationPrefrance.IN_APP
        "Push" -> NotificationPrefrance.PUSH
        "All" -> NotificationPrefrance.ALL
        else -> NotificationPrefrance.ALL
    }
}