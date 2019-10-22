package com.example.movienager.ui.movie.show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movienager.data.MovieRepository

class MovieDetailsViewModelFactory (
    private val repository: MovieRepository,
    private val imdbId: String
) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = MovieDetailsViewModel(
        repository,
        imdbId
    ) as T
}