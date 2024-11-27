package com.singhtwenty2.oceanvista.feature_auth.domain.repository

import com.singhtwenty2.oceanvista.feature_auth.domain.model.CheckEmailRequest
import com.singhtwenty2.oceanvista.feature_auth.domain.model.LoginRequest
import com.singhtwenty2.oceanvista.feature_auth.domain.model.RegisterRequest
import com.singhtwenty2.oceanvista.feature_auth.domain.model.UserDetailsResponse
import com.singhtwenty2.oceanvista.feature_auth.util.AuthApiResponseHandler

interface AuthRepository {

    suspend fun checkEmailExists(
        emailRequest: CheckEmailRequest
    ): AuthApiResponseHandler<Unit>

    suspend fun registerUser(
        registerRequest: RegisterRequest
    ): AuthApiResponseHandler<Unit>

    suspend fun loginUser(
        loginRequest: LoginRequest
    ): AuthApiResponseHandler<Unit>

    suspend fun fetchUserDetails(
        jwtToken: String
    ): AuthApiResponseHandler<UserDetailsResponse?>
}