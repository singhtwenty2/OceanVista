package com.singhtwenty2.oceanvista.feature_home.data.remote

import com.singhtwenty2.oceanvista.feature_home.data.remote.model.dto.request.UserLocationRequestDTO
import com.singhtwenty2.oceanvista.feature_home.util.LocationHttpRoutes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT

interface LocationApiService {

    @PUT(LocationHttpRoutes.UPDATE_LOCATION)
    suspend fun updateLocation(
        @Header("Authorization") token: String,
        @Body userLocationRequestDTO: UserLocationRequestDTO
    ): Response<Unit>
}