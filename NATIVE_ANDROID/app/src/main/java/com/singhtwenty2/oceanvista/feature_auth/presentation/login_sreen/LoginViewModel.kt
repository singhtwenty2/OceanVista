package com.singhtwenty2.oceanvista.feature_auth.presentation.login_sreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singhtwenty2.oceanvista.feature_auth.domain.model.LoginRequest
import com.singhtwenty2.oceanvista.feature_auth.domain.repository.AuthRepository
import com.singhtwenty2.oceanvista.feature_auth.util.AuthApiResponseHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository
): ViewModel() {

    val state = mutableStateOf(LoginState())
    private val loginResultChannel = Channel<AuthApiResponseHandler<Unit>>()
    val loginResult = loginResultChannel.receiveAsFlow()

    fun onEvent(events: LoginUiEvents) {
        when(events) {
            is LoginUiEvents.EmailChanged -> {
                state.value = state.value.copy(email = events.email)
            }
            is LoginUiEvents.PasswordChanged -> {
                state.value = state.value.copy(password = events.password)
            }
            LoginUiEvents.LoginClicked -> login()
        }
    }

    private fun login() {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            val result = repository.loginUser(
                LoginRequest(
                    email = state.value.email,
                    password = state.value.password
                )
            )
            loginResultChannel.send(result)
            state.value = state.value.copy(isLoading = false)
        }
    }
}