package com.example.movienager.ui.movie.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movienager.data.MovieRepository

class MovieViewModelFactory (
    private val repository: MovieRepository
) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = MovieViewModel(
        repository
    ) as T
}