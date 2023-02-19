package com.rajatkumar.unliminttestapp

import android.app.Application
import com.rajatkumar.unliminttestapp.data.jokeDataModule
import com.rajatkumar.unliminttestapp.domain.jokeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(jokeDataModule, jokeModule)
        }
    }
}