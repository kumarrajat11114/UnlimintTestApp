package com.rajatkumar.unliminttestapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rajatkumar.unliminttestapp.base.Resource
import com.rajatkumar.unliminttestapp.domain.usecases.GetJokesUseCase
import com.rajatkumar.unliminttestapp.domain.usecases.GetNewJokeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class JokeViewModel(
    private val getJokesUseCase: GetJokesUseCase,
    private val getNewJokeUseCase: GetNewJokeUseCase
): ViewModel() {

    fun getNewJoke(existingJokesSize: Int = 0) = getNewJokeUseCase.execute(existingJokesSize)

    fun getJokes() = getJokesUseCase.execute()

}