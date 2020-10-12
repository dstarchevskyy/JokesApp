package com.droiddevstar.jokeapp.repository


import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface JokeApi {

    @GET("/api")
    fun getJokeAsync(): Deferred<Response<String>>

}