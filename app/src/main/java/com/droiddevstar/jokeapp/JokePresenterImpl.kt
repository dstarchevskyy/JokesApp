package com.droiddevstar.jokeapp

import android.util.Log
import com.droiddevstar.jokeapp.repository.JokeRepository
import javax.inject.Inject

class JokePresenterImpl
@Inject
constructor(private val jokeRepository: JokeRepository) : JokePresenter {

    private var view: MainActivity? = null


    override fun getJokes() {
        val deferred = jokeRepository.getJokeAsync()
        Log.e("JokePresenterImpl", deferred.toString())

    }

    override fun attachView(mainActivity: MainActivity) {
        view = mainActivity
    }


    override fun detachView() {
        view = null
    }
}