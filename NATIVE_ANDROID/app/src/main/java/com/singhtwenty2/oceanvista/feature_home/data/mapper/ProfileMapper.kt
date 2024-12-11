package com.singhtwenty2.oceanvista.feature_home.data.mapper

import com.singhtwenty2.oceanvista.feature_home.data.remote.model.dto.response.ProfileResponseDTO
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.Profile

fun ProfileResponseDTO.toDomainModel(): Profile {
    return Profile(
        userId = user_id,
        userName = username,
        name = name,
        email = email,
        mobileNumber = phoneNumber,
        profilePic = "",
        notificationPrefrence = notificationPrefrence
    )
}