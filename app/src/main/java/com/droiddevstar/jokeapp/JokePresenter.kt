package com.droiddevstar.jokeapp

interface JokePresenter {

    fun getJokes()

    fun attachView(mainActivity: MainActivity)

    fun detachView()

}