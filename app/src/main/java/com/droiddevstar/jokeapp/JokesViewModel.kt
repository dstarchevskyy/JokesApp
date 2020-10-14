package com.droiddevstar.jokeapp

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droiddevstar.jokeapp.repository.JokeRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class JokesViewModel
@ViewModelInject
constructor(private val repository: JokeRepository,
            @Assisted
            private val savedStateHandle: SavedStateHandle): ViewModel() {

    val jokeLiveDate = MutableLiveData<String?>(null)

    fun loadNextJoke() {
        viewModelScope.launch {
            val response = repository.getJokeAsync().await()
            Timber.e("!!!response: $response")
            response.let {
                if (it.isSuccessful) {
                    Timber.e(it.toString())
                    Timber.e(it.body().toString())
//                    response.body()?.string()


                    val inputStream = it.body()?.byteStream()
                    val byteArray = inputStream?.readBytes()
                    val jokeReadableText = String(byteArray!!)
                    Timber.e(jokeReadableText)

                }
            }
        }
        Timber.e("!!!repository: $repository")


    }
}