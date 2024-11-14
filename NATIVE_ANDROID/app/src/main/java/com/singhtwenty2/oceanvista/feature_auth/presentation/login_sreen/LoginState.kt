package com.singhtwenty2.oceanvista.feature_auth.presentation.login_sreen

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String = ""
)
