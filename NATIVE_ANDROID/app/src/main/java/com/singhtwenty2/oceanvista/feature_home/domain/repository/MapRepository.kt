package com.singhtwenty2.oceanvista.feature_home.domain.repository

import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.BeachColorCode
import com.singhtwenty2.oceanvista.feature_home.util.HomeApiResponseHandler

interface MapRepository {

    suspend fun getBeachesColorCode(): List<BeachColorCode>
}