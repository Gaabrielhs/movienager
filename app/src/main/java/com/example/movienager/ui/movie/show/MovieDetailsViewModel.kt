package com.example.movienager.ui.movie.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movienager.data.Movie
import com.example.movienager.data.MovieRepository

class MovieDetailsViewModel internal constructor(
    private val movieRepository: MovieRepository,
    val movieImdbId: String
) : ViewModel() {

    val movie : LiveData<Movie>

    init {
        movie = movieRepository.getByImdbId(movieImdbId)
    }
}