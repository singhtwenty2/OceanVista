package com.singhtwenty2.oceanvista.feature_home.data.remote

import com.singhtwenty2.oceanvista.feature_home.data.remote.model.dto.response.ProfileResponseDTO
import com.singhtwenty2.oceanvista.feature_home.util.ProfileHttpRoutes
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileApiService {

    @GET(ProfileHttpRoutes.PROFILE)
    suspend fun fetchProfile(
        @Header("Authorization") token: String
    ): ProfileResponseDTO
}