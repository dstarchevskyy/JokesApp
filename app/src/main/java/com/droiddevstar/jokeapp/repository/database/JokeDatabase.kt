package com.droiddevstar.jokeapp.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [JokeModel::class], version = 1)
abstract class JokeDatabase : RoomDatabase() {

    abstract fun jokeDao(): JokeDao

}

const val JOKE_DATABASE_NAME = "JokeDatabase"