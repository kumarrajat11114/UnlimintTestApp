package com.rajatkumar.unliminttestapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rajatkumar.unliminttestapp.databinding.LayoutJokeItemBinding
import timber.log.Timber

class JokesAdapter(private val context: Context): RecyclerView.Adapter<JokesAdapter.JokeViewHolder>() {

    private val jokesList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        return JokeViewHolder(LayoutJokeItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.setJoke(jokesList[position])
    }

    override fun getItemCount(): Int = jokesList.size

    fun updateJokes(jokes: List<String>) {
        val diffCallback = JokesDiffCallback(oldJokesList = jokesList, newJokesList = jokes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        jokesList.clear()
        jokesList.addAll(jokes)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class JokeViewHolder(private val jokeBinding: LayoutJokeItemBinding): RecyclerView.ViewHolder(jokeBinding.root) {
        fun setJoke(joke: String) {
            jokeBinding.tvJoke.text = joke
        }
    }

    inner class JokesDiffCallback(
        private val oldJokesList: List<String>,
        private val newJokesList: List<String>
    ): DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldJokesList.size

        override fun getNewListSize(): Int = newJokesList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldJoke = oldJokesList[oldItemPosition]
            val newJoke = newJokesList[newItemPosition]
            return oldJoke == newJoke
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldJoke = oldJokesList[oldItemPosition]
            val newJoke = newJokesList[newItemPosition]
            return oldJoke == newJoke
        }

    }
}