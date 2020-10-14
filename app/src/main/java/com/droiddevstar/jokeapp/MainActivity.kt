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
        jokeViewModel.jokeLiveDate.observe(this, { onNewJoke(it) })
        jokeViewModel.startLoadingLoop()
        jokeViewModel.isLoading.observe(this, { onLoadingStatusChanged(it) } )
    }

    private fun configureRecyclerView() {
        binding.rvJokes.layoutManager = LinearLayoutManager(this)
        binding.rvJokes.adapter = adapter
    }

    private fun onNewJoke(newJoke: String?) {
        if (!newJoke.isNullOrBlank()) {
            jokesList.add(newJoke)
            while (jokesList.size > 10) {
                jokesList.removeAt(0)
            }

            adapter.notifyDataSetChanged()
        }
    }

    private fun showProgress() {
        binding.pbProgress.visibility = VISIBLE
    }

    private fun hideProgress() {
        binding.pbProgress.visibility = GONE
    }

    private fun onLoadingStatusChanged(isLoading: Boolean) {
        if (isLoading) {
            showProgress()
        } else {
            hideProgress()
        }
    }
}
