package com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singhtwenty2.oceanvista.feature_auth.domain.model.RegisterRequest
import com.singhtwenty2.oceanvista.feature_auth.domain.repository.AuthRepository
import com.singhtwenty2.oceanvista.feature_auth.util.AuthApiResponseHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: AuthRepository
): ViewModel() {

    var state = mutableStateOf(RegisterState())
    private val registerResultChannel = Channel<AuthApiResponseHandler<Unit>>()
    val registerResult = registerResultChannel.receiveAsFlow()

    fun onEvent(events: RegisterUiEvents) {
        when(events) {
            is RegisterUiEvents.EmailChanged -> {
                state.value = state.value.copy(email = events.email)
            }
            is RegisterUiEvents.NotificationPrefranceChanged -> {
                state.value = state.value.copy(notificationPrefrence = events.notificationPrefrance)
            }
            is RegisterUiEvents.PasswordChanged -> {
                state.value = state.value.copy(password = events.password)
            }
            is RegisterUiEvents.ConfirmPasswordChanged -> {
                state.value = state.value.copy(confirmPassword = events.confirmPassword)
            }
            RegisterUiEvents.RegisterClicked -> register()
        }
    }
    private fun register() {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            val result = repository.registerUser(
                RegisterRequest(
                    email = state.value.email,
                    password = state.value.password,
                    notificationPref = state.value.notificationPrefrence
                )
            )
            Log.d("Register", "register: ${state.value.notificationPrefrence}")
            registerResultChannel.send(result)
            state.value = state.value.copy(isLoading = false)

//            if (result is AuthResponseHandler.Success) {
//                state.value.isVerifyingEmail = true
//                checkEmailVerification()
//            }
        }
    }
//    private fun checkEmailVerification() {
//        viewModelScope.launch {
//            val result = repository.checkEmailExists(CheckEmailRequest(state.value.email))
//            when (result) {
//                is AuthResponseHandler.Conflict -> {
//                    state.value.isVerifyingEmail = false
//                }
//                else -> {
//                    state.value.isVerifyingEmail = false
//                    state.value.autoLoginFailed = true
//                    state.value.autoLoginErrorMessage = "Automatic login failed. Please try again."
//                }
//            }
//        }
//    }
}