package com.singhtwenty2.oceanvista.feature_auth.data.remote.repository

import android.content.SharedPreferences
import android.util.Log
import com.singhtwenty2.oceanvista.feature_auth.data.remote.AuthService
import com.singhtwenty2.oceanvista.feature_auth.data.mapper.toCheckEmailRequestDTO
import com.singhtwenty2.oceanvista.feature_auth.data.mapper.toLoginRequestDTO
import com.singhtwenty2.oceanvista.feature_auth.data.mapper.toRegisterRequestDTO
import com.singhtwenty2.oceanvista.feature_auth.data.remote.model.dto.request.CheckEmailRequestDTO
import com.singhtwenty2.oceanvista.feature_auth.domain.model.CheckEmailRequest
import com.singhtwenty2.oceanvista.feature_auth.domain.model.LoginRequest
import com.singhtwenty2.oceanvista.feature_auth.domain.model.RegisterRequest
import com.singhtwenty2.oceanvista.feature_auth.domain.model.UserDetailsResponse
import com.singhtwenty2.oceanvista.feature_auth.domain.repository.AuthRepository
import com.singhtwenty2.oceanvista.feature_auth.util.AuthResponseHandler
import com.singhtwenty2.oceanvista.feature_auth.util.handleAuthApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthRepositoryImpl(
    private val service: AuthService,
    private val pref: SharedPreferences
) : AuthRepository {

    override suspend fun checkEmailExists(emailRequest: CheckEmailRequest): AuthResponseHandler<Unit> {
        return handleAuthApiCall {
            service.checkEmail(emailRequest.toCheckEmailRequestDTO())
        }
    }

    override suspend fun registerUser(registerRequest: RegisterRequest): AuthResponseHandler<Unit> {
        return handleAuthApiCall {
            val response = service.register(registerRequest.toRegisterRequestDTO())
            if (response.isSuccessful) {
                pref.edit()
                    .putString("email", registerRequest.email)
                    .putString("password", registerRequest.password)
                    .putLong("registration_timestamp", System.currentTimeMillis())
                    .apply()
                scheduleRegistrationDataDeletion()
                checkEmailVerification()
            } else {
                Log.e("RegisterUser", "Registration failed: ${response.errorBody()?.string()}")
                throw Exception("Registration failed with error code: ${response.code()}")
            }
        }
    }

    private fun scheduleRegistrationDataDeletion() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(10 * 60 * 1000)
            clearRegistrationDataIfExpired()
        }
    }

    private fun clearRegistrationDataIfExpired() {
        val savedTime = pref.getLong("registration_timestamp", 0L)
        if (System.currentTimeMillis() - savedTime >= 10 * 60 * 1000) {
            pref.edit().remove("email").remove("password").remove("registration_timestamp").apply()
            Log.d("RegistrationData", "Registration data expired and removed.")
        }
    }

    private suspend fun autoLogin() {
        delay(1500)
        val email = pref.getString("email", null)
        val password = pref.getString("password", null)
        if (email != null && password != null) {
            val loginRequest = LoginRequest(email = email, password = password)
            loginUser(loginRequest)
        } else {
            Log.e("AutoLogin", "Registration data missing in SharedPreferences.")
        }
    }

    private fun checkEmailVerification() {
        CoroutineScope(Dispatchers.IO).launch {
            var retryCount = 0
            while (retryCount < 40) {
                delay(4500)
                val email = pref.getString("email", null)
                if (email != null) {
                    val response = service.checkEmail(CheckEmailRequestDTO(email = email))
                    if(response.code() == 409) {
                        autoLogin()
                        break
                    }
                } else {
                    Log.e("CheckEmailVerification", "Email missing in SharedPreferences.")
                    break
                }
                retryCount++
            }
        }
    }


    override suspend fun loginUser(loginRequest: LoginRequest): AuthResponseHandler<Unit> {
        return handleAuthApiCall {
            clearRegistrationDataIfExpired()
            val response = service.login(loginRequest.toLoginRequestDTO())
            if (response.isSuccessful) {
                response.body()?.token?.let { token ->
                    pref.edit()
                        .putString("jwt_token", token)
                        .putLong("jwt_token_time", System.currentTimeMillis())
                        .apply()
                    pref.edit()
                        .remove("email")
                        .remove("password")
                        .remove("registration_timestamp")
                        .apply()
                    Log.d("JWT", "Login successful: $token")
                    Log.d("JWT_TIME", "Login time: ${pref.getLong("jwt_token_time", 0L)}")
                } ?: run {
                    Log.e("LoginUser", "Token missing in login response")
                    throw Exception("Login response missing token")
                }
            } else {
                Log.e("LoginUser", "Login failed: ${response.errorBody()?.string()}")
                throw Exception("Login failed with error code: ${response.code()}")
            }
        }
    }


    override suspend fun fetchUserDetails(jwtToken: String): AuthResponseHandler<UserDetailsResponse?> {
        return handleAuthApiCall {
            val response = service.aboutUser("Bearer ${pref.getString("jwt_token", "")}")
            if (response.isSuccessful) {
                response.body()?.let {
                    UserDetailsResponse(
                        userName = it.userName,
                        email = it.email,
                        notificationPrefrence = it.notificationPrefrence
                    )
                } ?: throw Exception("User details missing in response")
            } else {
                Log.e("FetchUserDetails", "Failed to fetch details: ${response.errorBody()?.string()}")
                throw Exception("Error fetching details, code: ${response.code()}")
            }
        }
    }
}