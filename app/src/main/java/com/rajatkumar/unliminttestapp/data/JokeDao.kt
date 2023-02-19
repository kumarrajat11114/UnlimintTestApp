package com.rajatkumar.unliminttestapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rajatkumar.unliminttestapp.data.model.Joke
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDao {

    @Query("SELECT * FROM Jokes ORDER BY timestamp")
    fun getAllJokes(): Flow<List<Joke>?>

    @Query("SELECT id FROM Jokes ORDER BY timestamp ASC LIMIT 1")
    suspend fun getOldestJokeId(): Int

    @Query("UPDATE Jokes SET joke = :joke, timestamp = :timestamp WHERE id = :oldId")
    suspend fun updateJoke(joke: String, timestamp: Long, oldId: Int)

    @Insert
    suspend fun insertJoke(joke: Joke)

}