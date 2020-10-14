package com.droiddevstar.jokeapp

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.droiddevstar.jokeapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val jokeViewModel: JokesViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val jokesList = ArrayList<String>()
    private val adapter = JokesAdapter(jokesList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.clRoot)
        configureRecyclerView()
        jokeViewModel.jokeLiveDate.observe(this, { newData -> onNewJoke(newData) })
        jokeViewModel.startLoadingLoop()
        showProgress()
    }

    private fun configureRecyclerView() {
        binding.rvJokes.layoutManager = LinearLayoutManager(this)
        binding.rvJokes.adapter = adapter
    }

    private fun onNewJoke(newJoke: String?) {
        if (!newJoke.isNullOrBlank()) {
            jokesList.add(newJoke)
            adapter.notifyDataSetChanged()
        }

        hideProgress()
    }

    fun showProgress() {
        binding.pbProgress.visibility = VISIBLE
    }

    fun hideProgress() {
        binding.pbProgress.visibility = GONE
    }

}