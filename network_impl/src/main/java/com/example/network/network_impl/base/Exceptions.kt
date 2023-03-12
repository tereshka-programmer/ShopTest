package com.example.network.network_impl.base

open class AppException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
}

// BackendException with statusCode=401 is usually mapped to this exception
class AuthException(
    cause: Throwable
) : AppException(cause = cause)

class InvalidCredentialsException(cause: Exception) : AppException(cause = cause)

class ConnectionException(cause: Throwable) : AppException(cause = cause)

open class BackendException(
    val code: Int,
) : AppException()

class ParseBackendResponseException(
    cause: Throwable
) : AppException(cause = cause)

// ---