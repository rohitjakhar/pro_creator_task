package com.rohitjakhar.procreatortask.utils

sealed class Resource<T>(
    open val data: T? = null,
    open val message: String? = "",
    open val errorType: ErrorType = ErrorType.UNKNOWN
) {
    class Empty<T> : Resource<T>()

    class Loading<T> : Resource<T>()

    data class Success<T>(override val data: T? = null, override val message: String? = "") :
        Resource<T>(data, message)

    data class Error<T>(
        override val message: String? = "",
        override val data: T? = null,
        override val errorType: ErrorType = ErrorType.UNKNOWN
    ) : Resource<T>(data, message)
}

enum class ErrorType() {
    UNKNOWN, NO_INTERNET, TIME_OUT
}
