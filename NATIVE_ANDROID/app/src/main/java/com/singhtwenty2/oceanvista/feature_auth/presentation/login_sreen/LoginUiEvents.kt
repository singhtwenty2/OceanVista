package com.singhtwenty2.oceanvista.feature_auth.presentation.login_sreen

sealed class LoginUiEvents {
    data class EmailChanged(val email: String) : LoginUiEvents()
    data class PasswordChanged(val password: String) : LoginUiEvents()
    data object LoginClicked : LoginUiEvents()
}