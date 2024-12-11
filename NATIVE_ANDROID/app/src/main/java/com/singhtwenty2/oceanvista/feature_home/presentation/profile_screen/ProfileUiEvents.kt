package com.singhtwenty2.oceanvista.feature_home.presentation.profile_screen

import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.Profile

sealed class ProfileUiEvents {
    data object LoadProfile : ProfileUiEvents()
    data class EditProfile(val profile: Profile) : ProfileUiEvents()
    data object Logout : ProfileUiEvents()
}