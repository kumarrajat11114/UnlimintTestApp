package com.rajatkumar.unliminttestapp.base

sealed class JokeError(message: String): Throwable(message) {
    class InternalServerError(message: String = "Internal Server Error"): JokeError(message)
    class TimeoutError(message: String = "Api Timed out"): JokeError(message)
    class ApiError(message: String): JokeError(message)
    class NoInternetError(message: String = "Network connection does not exists!. Please connect to a network and try again"): JokeError(message)
    class DataNotFoundError(message: String = "Data Not Found"): JokeError(message)
}