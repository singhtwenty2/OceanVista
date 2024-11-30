package com.singhtwenty2.oceanvista.feature_home.presentation.beach_detail_screen

sealed class BeachDetailUiEvents {
    data object Retry : BeachDetailUiEvents()
    data object Refresh : BeachDetailUiEvents()
    data class PhotoSelected(val index: Int) : BeachDetailUiEvents()
    data class LikeBeach(val beachId: Long) : BeachDetailUiEvents()
    data class BookmarkBeach(val beachId: Long) : BeachDetailUiEvents()
    data class ReportIssue(val beachId: Long, val issue: String) : BeachDetailUiEvents()
    data class LogError(val message: String) : BeachDetailUiEvents()
    data class ShareBeach(val beachId: Long) : BeachDetailUiEvents()
    data class NavigateToBeachMap(val beachId: Long) : BeachDetailUiEvents()
    data class ViewAllPhotos(val beachId: Long) : BeachDetailUiEvents()
}

