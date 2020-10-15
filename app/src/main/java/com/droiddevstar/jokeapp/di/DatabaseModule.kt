package com.droiddevstar.jokeapp.di

import android.content.Context
import androidx.room.Room
import com.droiddevstar.jokeapp.repository.database.JOKE_DATABASE_NAME
import com.droiddevstar.jokeapp.repository.database.JokeDao
import com.droiddevstar.jokeapp.repository.database.JokeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) : JokeDatabase {
        return Room.databaseBuilder(
            appContext,
            JokeDatabase::class.java,
            JOKE_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideJokeDao(database: JokeDatabase) : JokeDao {
        return database.jokeDao()
    }
}