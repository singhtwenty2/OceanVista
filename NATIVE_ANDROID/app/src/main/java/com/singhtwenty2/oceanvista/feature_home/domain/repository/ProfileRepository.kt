package com.singhtwenty2.oceanvista.feature_home.domain.repository

import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.Profile
import com.singhtwenty2.oceanvista.feature_home.util.HomeApiResponseHandler

interface ProfileRepository {

    suspend fun fetchProfile(): HomeApiResponseHandler<Profile>

    suspend fun logout(): HomeApiResponseHandler<Boolean>
}