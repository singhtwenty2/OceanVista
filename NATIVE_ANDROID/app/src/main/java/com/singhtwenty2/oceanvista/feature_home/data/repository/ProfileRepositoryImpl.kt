package com.singhtwenty2.oceanvista.feature_home.data.repository

import android.content.SharedPreferences
import com.singhtwenty2.oceanvista.feature_home.data.mapper.toDomainModel
import com.singhtwenty2.oceanvista.feature_home.data.remote.ProfileApiService
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.Profile
import com.singhtwenty2.oceanvista.feature_home.domain.repository.ProfileRepository
import com.singhtwenty2.oceanvista.feature_home.util.HomeApiResponseHandler
import com.singhtwenty2.oceanvista.feature_home.util.handleHomeApiCall

class ProfileRepositoryImpl(
    private val profileApiService: ProfileApiService,
    private val pref: SharedPreferences
) : ProfileRepository {

    private val token = pref.getString("jwt_token", null) ?: ""

    override suspend fun fetchProfile(): HomeApiResponseHandler<Profile> {
        return handleHomeApiCall {
            val response = profileApiService.fetchProfile(
                token = "Bearer $token"
            )
            response.toDomainModel()
        }
    }

    override suspend fun logout(): HomeApiResponseHandler<Boolean> {
        return handleHomeApiCall {
            pref.edit().clear().apply()
            true
        }
    }
}