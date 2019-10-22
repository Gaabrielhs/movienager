package com.example.movienager.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movienager.data.Movie
import com.example.movienager.data.MovieRepository

class MovieViewModel internal constructor(private val movieRepository: MovieRepository) : ViewModel() {

    val movies : LiveData<List<Movie>> = movieRepository.getSearchCache()

    fun fetchMovies(searchQuery: String){
        movieRepository.fetchMovies(searchQuery)
    }

}