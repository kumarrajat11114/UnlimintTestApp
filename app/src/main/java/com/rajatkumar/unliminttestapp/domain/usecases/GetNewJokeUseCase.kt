package com.rajatkumar.unliminttestapp.domain.usecases

import android.os.CountDownTimer
import com.rajatkumar.unliminttestapp.base.Resource
import com.rajatkumar.unliminttestapp.data.repo.JokeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class GetNewJokeUseCase(private val jokeRepo: JokeRepo) {
    fun execute(existingJokesSize: Int) = flow<Resource<Unit>> {
        emit(Resource.ShowLoading())
        try {
            if (existingJokesSize > 0) {
                delay(10.seconds)
            }
            jokeRepo.getNewJoke(existingJokesSize == 10)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
        emit(Resource.HideLoading())
    }.flowOn(Dispatchers.IO)
}