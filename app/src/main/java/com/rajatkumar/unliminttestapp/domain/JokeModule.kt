package com.rajatkumar.unliminttestapp.domain

import com.rajatkumar.unliminttestapp.domain.usecases.GetJokesUseCase
import com.rajatkumar.unliminttestapp.domain.usecases.GetNewJokeUseCase
import com.rajatkumar.unliminttestapp.ui.JokeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val jokeModule = module {
    factory { GetJokesUseCase(get(),) }
    factory { GetNewJokeUseCase(get()) }
    viewModel { JokeViewModel(get(), get()) }
}