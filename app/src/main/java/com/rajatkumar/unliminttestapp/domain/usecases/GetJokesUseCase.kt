package com.rajatkumar.unliminttestapp.domain.usecases

import com.rajatkumar.unliminttestapp.base.JokeError
import com.rajatkumar.unliminttestapp.base.JokeException
import com.rajatkumar.unliminttestapp.data.repo.JokeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GetJokesUseCase(
    private val jokeRepo: JokeRepo
) {

    fun execute(): Flow<List<String>> = jokeRepo.getJokes()
        .map {
            it?.map { joke ->
                joke.joke
            } ?: run {
                throw JokeException(JokeError.DataNotFoundError())
            }
        }.flowOn(Dispatchers.IO)
}