package com.singhtwenty2.oceanvista.feature_home.data.remote

import com.singhtwenty2.oceanvista.feature_home.data.remote.model.dto.response.BeachColorCodeResposneDTO
import com.singhtwenty2.oceanvista.feature_home.util.MapHttpRoutes
import retrofit2.http.GET

interface MapApiService {

    @GET(MapHttpRoutes.BEACH_COLOR_CODE)
    suspend fun getBeachesColorCode(): BeachColorCodeResposneDTO
}