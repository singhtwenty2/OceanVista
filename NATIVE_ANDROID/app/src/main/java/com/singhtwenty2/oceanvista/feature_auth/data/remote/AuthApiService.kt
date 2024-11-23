package com.singhtwenty2.oceanvista.feature_auth.data.remote

import com.singhtwenty2.oceanvista.feature_auth.data.remote.model.dto.request.CheckEmailRequestDTO
import com.singhtwenty2.oceanvista.feature_auth.data.remote.model.dto.request.LoginRequestDTO
import com.singhtwenty2.oceanvista.feature_auth.data.remote.model.dto.request.RegisterRequestDTO
import com.singhtwenty2.oceanvista.feature_auth.data.remote.model.dto.response.LoginResponseDTO
import com.singhtwenty2.oceanvista.feature_auth.data.remote.model.dto.response.UserDetailsResponseDTO
import com.singhtwenty2.oceanvista.feature_auth.util.ConnectionUrl
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST(ConnectionUrl.CHECK_EMAIL)
    suspend fun checkEmail(
        @Body request: CheckEmailRequestDTO
    ): Response<Unit>

    @POST(ConnectionUrl.REGSITER_ROUTE)
    suspend fun register(
        @Body requestDto: RegisterRequestDTO
    ): Response<Unit>

    @POST(ConnectionUrl.LOGIN_ROUTE)
    suspend fun login(
        @Body request: LoginRequestDTO
    ): Response<LoginResponseDTO>

    @GET(ConnectionUrl.ABOUT_ROUTE)
    suspend fun aboutUser(
        @Header("Authorization") token: String
    ): Response<UserDetailsResponseDTO>
}