package com.singhtwenty2.oceanvista.feature_home.domain.repository

import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.request.UserLocationRequest
import com.singhtwenty2.oceanvista.feature_home.util.HomeApiResponseHandler

interface LocationRepository {

    suspend fun updateLocation(
        userLocationRequest: UserLocationRequest
    ): HomeApiResponseHandler<Unit>
}