package com.droiddevstar.jokeapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val jokeViewModel: JokesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.e("!!!jokeViewModel: $jokeViewModel")
        jokeViewModel.jokeLiveDate.observe(this, { newData -> tv_text.text = newData })
        jokeViewModel.loadNextJoke()
    }

}