package com.rajatkumar.unliminttestapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rajatkumar.unliminttestapp.base.BaseActivity
import com.rajatkumar.unliminttestapp.base.Resource
import com.rajatkumar.unliminttestapp.databinding.ActivityJokeBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class JokeActivity : BaseActivity<ActivityJokeBinding>() {

    private val jokeViewModel: JokeViewModel by viewModel()

    private val jokesAdapter = JokesAdapter(this)
    private val jokesLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    override fun inflateBinding(): ActivityJokeBinding =
        ActivityJokeBinding.inflate(LayoutInflater.from(this))

    override fun created() {
        setUpJokeRecyclerView()
        getJokes()
    }

    private fun setUpJokeRecyclerView() {
        binding.rvJokes.apply {
            adapter = jokesAdapter
            layoutManager = jokesLayoutManager
            setHasFixedSize(true)
        }
    }

    private fun getJokes() {
        lifecycleScope.launch {
            jokeViewModel.getJokes().collect {
                jokesAdapter.updateJokes(it)
                jokeViewModel.getNewJoke(it.size).collect { result ->
                    when (result) {
                        is Resource.ShowLoading -> {
                            Toast.makeText(this@JokeActivity, "Loading new joke", Toast.LENGTH_SHORT).show()
                            binding.jokeLoader.isInvisible = false
                        }
                        is Resource.HideLoading -> {
                            binding.jokeLoader.isInvisible = true
                        }
                        is Resource.Success -> {
                            Toast.makeText(this@JokeActivity, "Added new joke", Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Error -> {
                            showErrorView(result.error)
                        }
                        is Resource.Idle -> {}
                    }
                }
            }
        }
    }

}