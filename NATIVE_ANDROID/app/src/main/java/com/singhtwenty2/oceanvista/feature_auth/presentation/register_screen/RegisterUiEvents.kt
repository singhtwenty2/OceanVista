package com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen

sealed class RegisterUiEvents {
    data class EmailChanged(val email: String): RegisterUiEvents()
    data class PasswordChanged(val password: String): RegisterUiEvents()
    data class ConfirmPasswordChanged(val confirmPassword: String): RegisterUiEvents()
    data class NotificationPrefranceChanged(val notificationPrefrance: String): RegisterUiEvents()
    data object RegisterClicked: RegisterUiEvents()
}