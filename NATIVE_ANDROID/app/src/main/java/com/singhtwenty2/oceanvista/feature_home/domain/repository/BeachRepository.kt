package com.singhtwenty2.oceanvista.feature_home.domain.repository

import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.Beach
import com.singhtwenty2.oceanvista.feature_home.util.HomeApiResponseHandler

interface BeachRepository {
    suspend fun getBeaches(): HomeApiResponseHandler<List<Beach>>
}