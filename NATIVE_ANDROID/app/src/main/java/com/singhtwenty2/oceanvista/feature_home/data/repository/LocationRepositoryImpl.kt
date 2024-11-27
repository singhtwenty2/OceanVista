package com.singhtwenty2.oceanvista.feature_home.data.repository

import android.content.SharedPreferences
import com.singhtwenty2.oceanvista.feature_home.data.remote.LocationApiService
import com.singhtwenty2.oceanvista.feature_home.data.remote.model.dto.request.UserLocationRequestDTO
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.request.UserLocationRequest
import com.singhtwenty2.oceanvista.feature_home.domain.repository.LocationRepository
import com.singhtwenty2.oceanvista.feature_home.util.HomeApiResponseHandler
import com.singhtwenty2.oceanvista.feature_home.util.handleHomeApiCall

class LocationRepositoryImpl(
    private val api: LocationApiService,
    private val pref: SharedPreferences
) : LocationRepository {

    private val token = pref.getString("jwt_token", null) ?: ""

    override suspend fun updateLocation(userLocationRequest: UserLocationRequest): HomeApiResponseHandler<Unit> {
        return handleHomeApiCall {
            api.updateLocation(
                token = "Bearer $token",
                userLocationRequestDTO = UserLocationRequestDTO(
                    latitude = userLocationRequest.latitude,
                    longitude = userLocationRequest.longitude
                )
            )
        }
    }
}