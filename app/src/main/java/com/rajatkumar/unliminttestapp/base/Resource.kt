package com.rajatkumar.unliminttestapp.base

sealed class Resource<T> {
    class Idle<T>: Resource<T>()
    data class ShowLoading<T>(val progress: Int = 0): Resource<T>()
    data class HideLoading<T>(val progress: Int = 0): Resource<T>()
    data class Success<T>(val data: T): Resource<T>()
    data class Error<T>(val error: Throwable): Resource<T>()
}