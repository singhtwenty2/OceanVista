package com.singhtwenty2.oceanvista.feature_home.data.repository

import com.singhtwenty2.oceanvista.feature_home.data.mapper.toDomainList
import com.singhtwenty2.oceanvista.feature_home.data.remote.MapApiService
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.BeachColorCode
import com.singhtwenty2.oceanvista.feature_home.domain.repository.MapRepository

class MapRepositoryImpl(
    private val mapApiService: MapApiService
) : MapRepository {
    override suspend fun getBeachesColorCode(): List<BeachColorCode> {
        val response = mapApiService.getBeachesColorCode()
        return response.toDomainList()
    }

}