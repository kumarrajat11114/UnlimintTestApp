package com.rajatkumar.unliminttestapp.data

import androidx.room.Room
import androidx.room.RoomDatabase
import com.rajatkumar.unliminttestapp.BuildConfig.API_URL
import com.rajatkumar.unliminttestapp.data.repo.JokeRepo
import com.rajatkumar.unliminttestapp.data.repo.JokeRepoImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val jokeDataModule = module {
    factory<OkHttpClient> {
        OkHttpClient().newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
            )
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder().baseUrl(API_URL).client(get())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    factory<JokeService> {
        get<Retrofit>().create(JokeService::class.java)
    }

    single<JokeDatabase> {
        Room.databaseBuilder(
            get(),
            JokeDatabase::class.java,
            "joke_database"
        ).build()
    }

    single<JokeDao> {
        get<JokeDatabase>().jokeDao()
    }

    single<JokeRepo> { JokeRepoImpl(get(), get())}
}