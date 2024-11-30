package com.singhtwenty2.oceanvista.feature_home.data.remote

import com.singhtwenty2.oceanvista.feature_home.data.remote.model.dto.response.BeachDetailResponseDTO
import com.singhtwenty2.oceanvista.feature_home.data.remote.model.dto.response.BeachResponseDTO
import com.singhtwenty2.oceanvista.feature_home.util.BeachHttpRoutes
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface BeachApiService {

    @GET(BeachHttpRoutes.BEACH)
    suspend fun getBeaches(
        @Header("Authorization") token: String
    ): List<BeachResponseDTO>

    @GET(BeachHttpRoutes.BEACH_DETAIL)
    suspend fun getBeachDetail(
        @Header("Authorization") token: String,
        @Path("beachId") beachId: Long
    ): BeachDetailResponseDTO
}