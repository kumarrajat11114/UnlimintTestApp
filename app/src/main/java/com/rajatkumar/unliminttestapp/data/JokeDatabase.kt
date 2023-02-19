package com.rajatkumar.unliminttestapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rajatkumar.unliminttestapp.data.model.Joke

@Database(entities = [Joke::class], version = 1)
abstract class JokeDatabase: RoomDatabase() {
    abstract fun jokeDao(): JokeDao

}