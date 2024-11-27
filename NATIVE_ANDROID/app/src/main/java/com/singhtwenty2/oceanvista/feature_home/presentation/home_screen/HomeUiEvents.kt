package com.singhtwenty2.oceanvista.feature_home.presentation.home_screen

sealed class HomeUiEvents {
    data object OnLoad : HomeUiEvents()
    data object OnRetry : HomeUiEvents()
    data object OnBeachClicked : HomeUiEvents()
}