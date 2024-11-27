package com.singhtwenty2.oceanvista.feature_home.util

sealed class HomeApiResponseHandler<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : HomeApiResponseHandler<T>(data)
    class UnknownError<T>(message: String, data: T? = null) : HomeApiResponseHandler<T>(data, message)
    class UnAuthorized<T> : HomeApiResponseHandler<T>()
    class Conflict<T> : HomeApiResponseHandler<T>()
    class InternalServerError<T> : HomeApiResponseHandler<T>()
    class BadRequest<T> : HomeApiResponseHandler<T>()
}