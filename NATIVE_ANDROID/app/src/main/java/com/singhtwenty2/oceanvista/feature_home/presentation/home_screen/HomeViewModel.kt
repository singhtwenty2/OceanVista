package com.singhtwenty2.oceanvista.feature_home.presentation.home_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.Beach
import com.singhtwenty2.oceanvista.feature_home.domain.repository.BeachRepository
import com.singhtwenty2.oceanvista.feature_home.util.HomeApiResponseHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val beachRepository: BeachRepository
) : ViewModel() {

    val state = mutableStateOf(HomeState())
    private val beachChannel = Channel<HomeApiResponseHandler<List<Beach>>>()
    val beachResult = beachChannel.receiveAsFlow()

    init {
        getBeaches()
    }

    fun onEvent(event: HomeUiEvents) {
        when(event) {
            HomeUiEvents.OnLoad -> {
                TODO()
            }
            HomeUiEvents.OnBeachClicked -> TODO()
            HomeUiEvents.OnRetry -> TODO()
        }
    }
    private fun getBeaches() {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true, isError = false, errorMessage = "")
            when (val result = beachRepository.getBeaches()) {
                is HomeApiResponseHandler.Success -> {
                    state.value = state.value.copy(
                        isLoading = false,
                        beaches = result.data ?: emptyList()
                    )
                    Log.d("HomeViewModel", result.data.toString())
                }
                is HomeApiResponseHandler.UnAuthorized -> {
                    state.value = state.value.copy(
                        isLoading = false,
                        isError = true,
                        errorMessage = result.message ?: "UnAuthorized"
                    )
                }

                else -> {
                    state.value = state.value.copy(
                        isLoading = false,
                        isError = true,
                        errorMessage = "Unknown Error"
                    )
                }
            }
        }
    }

}