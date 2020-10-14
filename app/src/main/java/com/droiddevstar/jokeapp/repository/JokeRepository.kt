package com.droiddevstar.jokeapp.repository

import kotlinx.coroutines.Deferred
import retrofit2.Response
import javax.inject.Inject

class JokeRepository @Inject constructor(private val jokeApi: JokeApi) {

    fun getJokeAsync(): Deferred<Response<String>> {
        return jokeApi.getJokeAsync()
    }

}