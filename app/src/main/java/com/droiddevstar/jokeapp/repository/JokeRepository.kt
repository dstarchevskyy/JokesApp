package com.droiddevstar.jokeapp.repository

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class JokeRepository @Inject constructor(private val jokeApi: JokeApi) {

    fun getJokeAsync(): Deferred<Response<ResponseBody>> {
        return jokeApi.getJokeAsync()
    }

}