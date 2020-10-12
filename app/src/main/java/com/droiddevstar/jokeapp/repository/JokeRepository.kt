package com.droiddevstar.jokeapp.repository

import kotlinx.coroutines.Deferred
import retrofit2.Response

interface JokeRepository {

    fun getJokeAsync(): Deferred<Response<String>>

}