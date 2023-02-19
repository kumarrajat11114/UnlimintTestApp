package com.rajatkumar.unliminttestapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Jokes")
data class Joke (
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        @ColumnInfo(name = "joke")
        val joke: String,
        @ColumnInfo(name = "timestamp")
        val timestamp: Long
)