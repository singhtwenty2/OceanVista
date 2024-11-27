package com.singhtwenty2.oceanvista.feature_auth.util

sealed class AuthApiResponseHandler<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : AuthApiResponseHandler<T>(data)
    class UnknownError<T>(message: String, data: T? = null) : AuthApiResponseHandler<T>(data, message)
    class UnAuthorized<T> : AuthApiResponseHandler<T>()
    class Conflict<T> : AuthApiResponseHandler<T>()
    class InternalServerError<T> : AuthApiResponseHandler<T>()
    class BadRequest<T> : AuthApiResponseHandler<T>()
}