package com.singhtwenty2.oceanvista.feature_home.presentation.home_screen

import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.Beach

data class HomeState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val beaches: List<Beach> = emptyList()
)
