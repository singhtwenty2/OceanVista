package com.singhtwenty2.oceanvista.feature_home.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.singhtwenty2.oceanvista.feature_home.data.mapper.toBeach
import com.singhtwenty2.oceanvista.feature_home.data.mapper.toDomainModel
import com.singhtwenty2.oceanvista.feature_home.data.remote.BeachApiService
import com.singhtwenty2.oceanvista.feature_home.data.remote.model.dto.response.BeachDetailResponseDTO
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.Beach
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.FullBeachDetailDomainModel
import com.singhtwenty2.oceanvista.feature_home.domain.repository.BeachRepository
import com.singhtwenty2.oceanvista.feature_home.util.HomeApiResponseHandler
import com.singhtwenty2.oceanvista.feature_home.util.handleHomeApiCall

class BeachRepositoryImpl(
    private val beachApiService: BeachApiService,
    private val pref: SharedPreferences
): BeachRepository {

    private val token = pref.getString("jwt_token", null) ?: ""

    override suspend fun getBeaches(): HomeApiResponseHandler<List<Beach>> {
        return handleHomeApiCall {
            val response = beachApiService.getBeaches(
                token = "Bearer $token"
            )
            response.map { it.toBeach() }
        }
    }

    override suspend fun getBeachDetail(beachId: Long): HomeApiResponseHandler<FullBeachDetailDomainModel> {
        return handleHomeApiCall {
            val response = beachApiService.getBeachDetail(
                token = "Bearer $token",
                beachId = beachId
            )
            Log.d("BeachRepository", response.toString())
            Log.d("BeachRepositoryImpl", response.toDomainModel().toString())
            response.toDomainModel()
        }
    }
}