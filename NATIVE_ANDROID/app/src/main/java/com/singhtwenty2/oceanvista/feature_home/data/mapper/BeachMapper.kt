package com.singhtwenty2.oceanvista.feature_home.data.mapper

import com.singhtwenty2.oceanvista.feature_home.data.remote.model.dto.response.BeachResponseDTO
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.Beach

fun BeachResponseDTO.toBeach(): Beach {
    return Beach(
        id = id.toString(),
        name = name,
        latitude = latitude.toString(),
        longitude = longitude.toString(),
        region = region,
        openingTime = openingTime,
        closingTime = closingTime,
        activities = activities,
        photos = photos
    )
}