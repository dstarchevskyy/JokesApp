package com.droiddevstar.jokeapp

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droiddevstar.jokeapp.Util.Companion.replaceEscapeChars
import com.droiddevstar.jokeapp.repository.JokeRepository
import kotlinx.coroutines.*
import timber.log.Timber
import java.lang.Exception

class JokesViewModel
@ViewModelInject
constructor(private val repository: JokeRepository): ViewModel() {

    val jokeLiveDate = MutableLiveData<String?>(null)

    fun startLoadingLoop() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                while (true) {
                    loadNextJoke()
                    delay(2000)
                }
            }
        }
    }

    private suspend fun loadNextJoke() {
        try {
            val response = repository.getJokeAsync().await()
            Timber.e("!!!response: $response")
                response.let {
                    if (it.isSuccessful) {
                        Timber.e(it.toString())
                        Timber.e(it.body().toString())

                        val inputStream = it.body()?.byteStream()
                        val byteArray = inputStream?.readBytes()
                        val jokeReadableText = replaceEscapeChars(String(byteArray!!))
                        Timber.e(jokeReadableText)
                        jokeLiveDate.postValue(jokeReadableText)
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
        }
    }
}

const val LOADING_INTERVAL = 2000  // TODO: set 1 minute: (60*1000).toLong()