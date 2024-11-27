package com.singhtwenty2.oceanvista.feature_auth.util

import retrofit2.HttpException
import java.io.IOException

suspend fun <T> handleAuthApiCall(apiCall: suspend () -> T): AuthApiResponseHandler<T> {
    return try {
        val result = apiCall.invoke()
        AuthApiResponseHandler.Success(result)
    } catch (e: HttpException) {
        when(e.code()) {
            400 -> AuthApiResponseHandler.BadRequest()
            401 -> AuthApiResponseHandler.UnAuthorized()
            409 -> AuthApiResponseHandler.Conflict()
            500 -> AuthApiResponseHandler.InternalServerError()
            else -> AuthApiResponseHandler.UnknownError("Unknown error occurred")
        }
    } catch (e: IOException) {
        AuthApiResponseHandler.UnknownError("Network error occurred")
    } catch (e: Exception) {
        AuthApiResponseHandler.UnknownError("Unknown error occurred")
    }
}