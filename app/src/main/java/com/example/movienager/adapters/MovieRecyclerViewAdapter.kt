package com.example.movienager.adapters


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.navigation.findNavController

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import com.example.movienager.data.Movie
import com.example.movienager.databinding.FragmentMovieBinding
import com.example.movienager.ui.movie.list.MovieListFragmentDirections


class MovieRecyclerViewAdapter : ListAdapter<Movie, MovieRecyclerViewAdapter.ViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)

        holder.apply {
            bind(movie, createOnClickListener(movie.imdbID))
            itemView.tag = movie
        }
    }

    private fun createOnClickListener(imdbId: String) : View.OnClickListener{
        return View.OnClickListener {
            val direction = MovieListFragmentDirections.actionNavigationMovieToMovieDetailsFragment(imdbId)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(private val binding: FragmentMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie, clickListener: View.OnClickListener){
            binding.apply {
                movie = item
                this.clickListener = clickListener
            }
        }
    }
}

private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>(){
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.imdbID == newItem.imdbID
    }
}
