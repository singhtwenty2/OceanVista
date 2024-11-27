package com.singhtwenty2.oceanvista.feature_home.data.remote

import com.singhtwenty2.oceanvista.feature_home.data.remote.model.dto.response.BeachResponseDTO
import com.singhtwenty2.oceanvista.feature_home.util.BeachHttpRoutes
import retrofit2.http.GET
import retrofit2.http.Header

interface BeachApiService {

    @GET(BeachHttpRoutes.BEACH)
    suspend fun getBeaches(
        @Header("Authorization") token: String
    ): List<BeachResponseDTO>
}