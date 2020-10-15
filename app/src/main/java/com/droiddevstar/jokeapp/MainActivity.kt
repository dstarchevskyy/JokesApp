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
    private val adapter = JokesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.clRoot)
        configureRecyclerView()
        jokeViewModel.jokesLiveData.observe(this, { onNewJokes(it) })
        jokeViewModel.startLoadingLoop()
        jokeViewModel.isLoading.observe(this, { onLoadingStatusChanged(it) })
    }

    private fun configureRecyclerView() {
        binding.rvJokes.layoutManager = LinearLayoutManager(this)
        binding.rvJokes.adapter = adapter
    }

    private fun onNewJokes(newJokes: List<String>) {
        adapter.updateList(newJokes)
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

    override fun onStop() {
        super.onStop()
        jokeViewModel.save()
    }
}
