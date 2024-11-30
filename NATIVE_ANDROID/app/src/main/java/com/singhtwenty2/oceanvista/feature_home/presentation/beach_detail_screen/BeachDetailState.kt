package com.singhtwenty2.oceanvista.feature_home.presentation.beach_detail_screen

import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.FullBeachDetailDomainModel

data class BeachDetailState(
    val isLoading: Boolean = false,
    val beachDetail: FullBeachDetailDomainModel? = null,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val isRefreshing: Boolean = false,
    val showSnackbar: String? = null,
    val selectedPhotoIndex: Int = 0,
    val userInteractionState: UserInteractionState? = null
)

sealed class UserInteractionState {
    data object Idle : UserInteractionState()
    data class Liked(val beachId: Long) : UserInteractionState()
    data class Unliked(val beachId: Long) : UserInteractionState()
    data class Bookmarked(val beachId: Long) : UserInteractionState()
    data class Unbookmarked(val beachId: Long) : UserInteractionState()
    data class ReportedIssue(val beachId: Long, val issue: String) : UserInteractionState()
    data class Loading(val action: String) : UserInteractionState()
    data class Error(val action: String, val message: String) : UserInteractionState()
}



