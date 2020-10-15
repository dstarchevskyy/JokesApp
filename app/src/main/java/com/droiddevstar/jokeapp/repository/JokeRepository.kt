package com.droiddevstar.jokeapp.repository

import com.droiddevstar.jokeapp.repository.database.JokeDao
import com.droiddevstar.jokeapp.repository.database.JokeModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class JokeRepository @Inject constructor(private val jokeApi: JokeApi,
                                         private val jokeDao: JokeDao) {

    fun getJokeAsync(): Deferred<Response<ResponseBody>> {
        return jokeApi.getJokeAsync()
    }

    suspend fun getJokesFromDB() : Flow<List<JokeModel>> {
        return jokeDao.getAllJoke()
    }

    fun saveJokesToDb(list: List<JokeModel>) {
        Timber.e("!!!SAVE: $list")
        jokeDao.insertAll(list)
    }

}