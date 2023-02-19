package com.rajatkumar.unliminttestapp.data.repo

import com.rajatkumar.unliminttestapp.data.model.Joke
import kotlinx.coroutines.flow.Flow

interface JokeRepo {
    suspend fun getNewJoke(update: Boolean)
    fun getJokes(): Flow<List<Joke>?>
}