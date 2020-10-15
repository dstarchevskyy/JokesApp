package com.droiddevstar.jokeapp.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDao {

    @Query("SELECT * FROM $JOKES_TABLE_NAME")
    fun getAllJoke(): Flow<List<JokeModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(jokeModel: JokeModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(jokesList: List<JokeModel>)

}