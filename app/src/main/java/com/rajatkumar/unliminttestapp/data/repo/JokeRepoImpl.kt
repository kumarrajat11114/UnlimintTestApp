package com.rajatkumar.unliminttestapp.data.repo

import com.rajatkumar.unliminttestapp.base.JokeError
import com.rajatkumar.unliminttestapp.base.JokeException
import com.rajatkumar.unliminttestapp.data.JokeDao
import com.rajatkumar.unliminttestapp.data.JokeService
import com.rajatkumar.unliminttestapp.data.model.Joke
import kotlinx.coroutines.flow.*
import timber.log.Timber

class JokeRepoImpl(private val jokeService: JokeService, private val jokeDao: JokeDao): JokeRepo {
    override suspend fun getNewJoke(update: Boolean) {
        val result = jokeService.getJoke()
        if (result.isSuccessful) {
            val body = result.body()
            if (body == null) {
                throw JokeException(JokeError.InternalServerError())
            } else {
                if (update) {
                    Timber.d("Updating Joke")
                    val oldestJokeId = jokeDao.getOldestJokeId()
                    jokeDao.updateJoke(body, System.currentTimeMillis(), oldestJokeId)
                } else {
                    Timber.d("Adding new Joke")
                    jokeDao.insertJoke(Joke(joke = body, timestamp = System.currentTimeMillis()))
                }
            }
        } else {
            throw JokeException(JokeError.ApiError(result.errorBody()?.string() ?: ""))
        }
    }

    override fun getJokes(): Flow<List<Joke>?> = jokeDao.getAllJokes()
}