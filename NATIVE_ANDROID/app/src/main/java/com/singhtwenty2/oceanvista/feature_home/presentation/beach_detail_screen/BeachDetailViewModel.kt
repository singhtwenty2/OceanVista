package com.singhtwenty2.oceanvista.feature_home.presentation.beach_detail_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singhtwenty2.oceanvista.feature_home.domain.repository.BeachRepository
import com.singhtwenty2.oceanvista.feature_home.util.HomeApiResponseHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class BeachDetailViewModel(
    private val beachRepository: BeachRepository,
    private val beachId: Long
) : ViewModel() {

    var state = mutableStateOf(BeachDetailState())
    private val eventChannel = Channel<BeachDetailUiEvents>(Channel.BUFFERED)
    val events = eventChannel.receiveAsFlow()

    init {
        getBeachDetail()
    }

    fun onEvent(event: BeachDetailUiEvents) {
        when (event) {
            is BeachDetailUiEvents.BookmarkBeach -> bookmarkBeach(event.beachId)
            is BeachDetailUiEvents.LikeBeach -> likeBeach(event.beachId)
            is BeachDetailUiEvents.LogError -> logError(event.message)
            is BeachDetailUiEvents.NavigateToBeachMap -> navigateToBeachMap(event.beachId)
            is BeachDetailUiEvents.PhotoSelected -> selectPhoto(event.index)
            BeachDetailUiEvents.Refresh -> refreshBeachDetails()
            is BeachDetailUiEvents.ReportIssue -> reportIssue(event.beachId, event.issue)
            BeachDetailUiEvents.Retry -> retryLoadingDetails()
            is BeachDetailUiEvents.ShareBeach -> shareBeach(event.beachId)
            is BeachDetailUiEvents.ViewAllPhotos -> TODO()
        }
    }

    private fun getBeachDetail() {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true, errorMessage = null)

            when (val result = beachRepository.getBeachDetail(beachId)) {
                is HomeApiResponseHandler.Success -> {
                    state.value = state.value.copy(
                        isLoading = false,
                        beachDetail = result.data
                    )
                }

                is HomeApiResponseHandler.UnAuthorized -> {
                    state.value = state.value.copy(
                        isLoading = false,
                        errorMessage = "Unauthorized access. Please log in again."
                    )
                    emitEvent(BeachDetailUiEvents.LogError("Unauthorized access"))
                }

                is HomeApiResponseHandler.UnknownError -> {
                    state.value = state.value.copy(
                        isLoading = false,
                        errorMessage = "An unknown error occurred. Please try again later."
                    )
                    emitEvent(BeachDetailUiEvents.LogError("Unknown error"))
                }

                else -> {
                    state.value = state.value.copy(
                        isLoading = false,
                        errorMessage = "An unexpected error occurred."
                    )
                    emitEvent(BeachDetailUiEvents.LogError("Unexpected error"))
                }
            }
        }
    }

    private fun refreshBeachDetails() {
        getBeachDetail()
    }

    private fun bookmarkBeach(beachId: Long) {
        viewModelScope.launch {
            state.value =
                state.value.copy(userInteractionState = UserInteractionState.Bookmarked(beachId))
            emitEvent(BeachDetailUiEvents.BookmarkBeach(beachId))
        }
    }

    private fun likeBeach(beachId: Long) {
        viewModelScope.launch {
            state.value =
                state.value.copy(userInteractionState = UserInteractionState.Liked(beachId))
            emitEvent(BeachDetailUiEvents.LikeBeach(beachId))
        }
    }

    private fun navigateToBeachMap(beachId: Long) {
        emitEvent(BeachDetailUiEvents.NavigateToBeachMap(beachId))
    }

    private fun selectPhoto(index: Int) {
        state.value = state.value.copy(selectedPhotoIndex = index)
    }

    private fun reportIssue(beachId: Long, issue: String) {
        viewModelScope.launch {
            emitEvent(BeachDetailUiEvents.ReportIssue(beachId, issue))
        }
    }

    private fun retryLoadingDetails() {
        getBeachDetail()
    }

    private fun shareBeach(beachId: Long) {
        emitEvent(BeachDetailUiEvents.ShareBeach(beachId))
    }

    private fun logError(message: String) {
        emitEvent(BeachDetailUiEvents.LogError(message))
    }

    private fun emitEvent(event: BeachDetailUiEvents) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }
}
