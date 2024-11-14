package com.singhtwenty2.oceanvista.feature_auth.domain.repository

import com.singhtwenty2.oceanvista.feature_auth.domain.model.CheckEmailRequest
import com.singhtwenty2.oceanvista.feature_auth.domain.model.LoginRequest
import com.singhtwenty2.oceanvista.feature_auth.domain.model.RegisterRequest
import com.singhtwenty2.oceanvista.feature_auth.domain.model.UserDetailsResponse
import com.singhtwenty2.oceanvista.feature_auth.util.AuthResponseHandler

interface AuthRepository {

    suspend fun checkEmailExists(
        emailRequest: CheckEmailRequest
    ): AuthResponseHandler<Unit>

    suspend fun registerUser(
        registerRequest: RegisterRequest
    ): AuthResponseHandler<Unit>

    suspend fun loginUser(
        loginRequest: LoginRequest
    ): AuthResponseHandler<Unit>

    suspend fun fetchUserDetails(
        jwtToken: String
    ): AuthResponseHandler<UserDetailsResponse?>
}