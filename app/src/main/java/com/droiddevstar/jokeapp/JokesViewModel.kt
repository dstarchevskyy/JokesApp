package com.droiddevstar.jokeapp

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.droiddevstar.jokeapp.repository.JokeRepository

class JokesViewModel
@ViewModelInject
constructor(private val repository: JokeRepository,
            @Assisted
            private val savedStateHandle: SavedStateHandle): ViewModel() {

    val jokeLiveDate = MutableLiveData<String?>(null)

    fun loadNextJoke() {
        

    }
}