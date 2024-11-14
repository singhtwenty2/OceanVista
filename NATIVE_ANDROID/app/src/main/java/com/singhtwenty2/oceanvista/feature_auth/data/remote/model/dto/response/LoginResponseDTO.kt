package com.singhtwenty2.oceanvista.feature_auth.data.remote.model.dto.response

data class LoginResponseDTO(
    val successMessage: String? = null,
    val token: String? = null,
    val errorMessage: String? = null
)
