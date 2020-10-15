package com.droiddevstar.jokeapp.repository.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = JOKES_TABLE_NAME)
class JokeModel(@PrimaryKey(autoGenerate = true)
                val id: Int = 0,

                val jokeText: String)

const val JOKES_TABLE_NAME = "Jokes"