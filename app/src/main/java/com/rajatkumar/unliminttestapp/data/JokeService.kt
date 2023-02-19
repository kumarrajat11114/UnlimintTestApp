package com.rajatkumar.unliminttestapp.data

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface JokeService {
    @GET("api")
    suspend fun getJoke(): Response<String>
}