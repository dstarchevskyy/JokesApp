package com.droiddevstar.jokeapp

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droiddevstar.jokeapp.Util.Companion.replaceEscapeChars
import com.droiddevstar.jokeapp.repository.JokeRepository
import com.droiddevstar.jokeapp.repository.database.JokeModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import java.lang.Exception

class JokesViewModel
@ViewModelInject
constructor(private val repository: JokeRepository): ViewModel() {

    private val jokesList = ArrayList<String>()
    val jokesLiveData = MutableLiveData<MutableList<String>>()
    val isLoading = MutableLiveData(false)

    fun startLoadingLoop() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                repository.getJokesFromDB().collect {
                    it.all { jokeModel ->
                        jokesList.add(jokeModel.jokeText)
                    }
                }
            }
        }

        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                while (true) {
                    loadNextJoke()
                    delay(LOADING_INTERVAL)
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

    fun save() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                Timber.e("!!!SAVE")
                repository.saveJokesToDb(convertToDbList())
            }
        }
    }

    private fun convertToDbList(): List<JokeModel> {
        val dbModelList = arrayListOf<JokeModel>()
        jokesList.forEach {
            dbModelList.add(JokeModel(jokeText = it))
        }

        return dbModelList
    }
}

const val LOADING_INTERVAL = 3000L  // TODO: set 1 minute: (60*1000).toLong()
const val MAX_JOKES_IN_LIST = 3 // TODO: 10 by task