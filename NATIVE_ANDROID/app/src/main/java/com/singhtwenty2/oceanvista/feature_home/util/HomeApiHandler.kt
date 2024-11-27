package com.singhtwenty2.oceanvista.feature_home.util

import retrofit2.HttpException
import java.io.IOException

suspend fun <T> handleHomeApiCall(apiCall: suspend () -> T): HomeApiResponseHandler<T> {
    return try {
        val result = apiCall.invoke()
        HomeApiResponseHandler.Success(result)
    } catch (e: HttpException) {
        when(e.code()) {
            400 -> HomeApiResponseHandler.BadRequest()
            401 -> HomeApiResponseHandler.UnAuthorized()
            409 -> HomeApiResponseHandler.Conflict()
            500 -> HomeApiResponseHandler.InternalServerError()
            else -> HomeApiResponseHandler.UnknownError("Unknown error occurred")
        }
    } catch (e: IOException) {
        HomeApiResponseHandler.UnknownError("Network error occurred")
    } catch (e: Exception) {
        HomeApiResponseHandler.UnknownError("Unknown error occurred")
    }
}