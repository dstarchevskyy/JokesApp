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

//    val jokeLiveDate = MutableLiveData<String?>(null)
    private val jokesList = ArrayList<String>()
    val jokesLiveData = MutableLiveData<MutableList<String>>()
    val isLoading = MutableLiveData(false)

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
            isLoading.postValue(true)
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
                        jokesList.add(jokeReadableText)
                        while (jokesList.size > MAX_JOKES_IN_LIST) {
                            jokesList.removeAt(0)
                        }
                        jokesLiveData.postValue(jokesList)
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
        } finally {
            isLoading.postValue(false)
        }
    }
}

const val LOADING_INTERVAL = 3000  // TODO: set 1 minute: (60*1000).toLong()
const val MAX_JOKES_IN_LIST = 3 // TODO: 10 by task