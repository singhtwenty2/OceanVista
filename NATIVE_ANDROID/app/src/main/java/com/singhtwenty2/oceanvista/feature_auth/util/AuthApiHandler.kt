package com.singhtwenty2.oceanvista.feature_auth.util

import retrofit2.HttpException
import java.io.IOException

suspend fun <T> handleAuthApiCall(apiCall: suspend () -> T): AuthResponseHandler<T> {
    return try {
        val result = apiCall.invoke()
        AuthResponseHandler.Success(result)
    } catch (e: HttpException) {
        when(e.code()) {
            400 -> AuthResponseHandler.BadRequest()
            401 -> AuthResponseHandler.UnAuthorized()
            409 -> AuthResponseHandler.Conflict()
            500 -> AuthResponseHandler.InternalServerError()
            else -> AuthResponseHandler.UnknownError("Unknown error occurred")
        }
    } catch (e: IOException) {
        AuthResponseHandler.UnknownError("Network error occurred")
    } catch (e: Exception) {
        AuthResponseHandler.UnknownError("Unknown error occurred")
    }
}