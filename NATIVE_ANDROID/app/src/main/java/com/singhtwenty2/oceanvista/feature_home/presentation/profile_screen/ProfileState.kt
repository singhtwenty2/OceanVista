package com.singhtwenty2.oceanvista.feature_home.presentation.profile_screen

import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.Profile

data class ProfileState(
    val isLoading: Boolean = false,
    val profile: Profile = Profile("", "", "", "", "", "", ""),
    val isError: Boolean = false,
    val errorMessage: String = "",
    val isLogout: Boolean = false,
    val isEditProfile: Boolean = false,
    val isEditProfileSuccess: Boolean = false,
)
