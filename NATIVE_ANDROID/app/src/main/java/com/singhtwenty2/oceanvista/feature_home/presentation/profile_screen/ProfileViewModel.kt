package com.singhtwenty2.oceanvista.feature_home.presentation.profile_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singhtwenty2.oceanvista.feature_home.domain.repository.ProfileRepository
import com.singhtwenty2.oceanvista.feature_home.util.HomeApiResponseHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    var state = mutableStateOf(ProfileState())
    private val eventChannel = Channel<ProfileUiEvents>(Channel.BUFFERED)
    val profileResult = eventChannel.receiveAsFlow()

    init {
        onEvent(ProfileUiEvents.LoadProfile)
    }

    fun onEvent(event: ProfileUiEvents) {
        when(event) {
            ProfileUiEvents.LoadProfile -> loadProfile()
            is ProfileUiEvents.EditProfile -> TODO()
            ProfileUiEvents.Logout -> logout()
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            when(val result = profileRepository.fetchProfile()) {
                is HomeApiResponseHandler.Success -> {
                    state.value = result.data?.let {
                        state.value.copy(
                            isLoading = false,
                            profile = it
                        )
                    } ?: state.value.copy(
                        isLoading = false,
                        errorMessage = "Profile not found"
                    )
                }
                is HomeApiResponseHandler.UnAuthorized -> {
                    state.value = state.value.copy(
                        isLoading = false,
                        errorMessage = "Unauthorized access. Please log in again."
                    )
                }

                else -> {
                    state.value = state.value.copy(
                        isLoading = false,
                        errorMessage = "An error occurred. Please try again later."
                    )
                }
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            when(profileRepository.logout()) {
                is HomeApiResponseHandler.Success -> {
                    state.value = state.value.copy(
                        isLoading = false,
                    )
                }
                else -> {
                    state.value = state.value.copy(
                        isLoading = false,
                        errorMessage = "An error occurred. Please try again later."
                    )
                }
            }
        }
    }
}