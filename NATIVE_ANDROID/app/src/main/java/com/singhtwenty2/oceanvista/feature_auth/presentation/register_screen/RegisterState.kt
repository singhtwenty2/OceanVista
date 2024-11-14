package com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val notificationPrefrence: String = "",
    var isVerifyingEmail: Boolean = false,
    var autoLoginFailed: Boolean = false,
    var autoLoginErrorMessage: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
)
