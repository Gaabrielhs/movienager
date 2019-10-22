package com.example.movienager.utils

import android.content.Context
import com.example.movienager.data.AppDatabase
import com.example.movienager.data.MovieRepository
import com.example.movienager.ui.movie.list.MovieViewModelFactory
import com.example.movienager.ui.movie.show.MovieDetailsViewModel
import com.example.movienager.ui.movie.show.MovieDetailsViewModelFactory

object InjectorUtils {

    private fun getMovieRepository(context: Context) : MovieRepository {
        return MovieRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).movieDao())
    }

    fun provideMovieViewModelFactory ( context: Context) : MovieViewModelFactory {
        val repository = getMovieRepository(context)
        return MovieViewModelFactory(repository)
    }

    fun provideMovieDetailsViewModelFactory(context: Context, imdbId: String) : MovieDetailsViewModelFactory {
        val repository = getMovieRepository(context)
        return MovieDetailsViewModelFactory(repository, imdbId)
    }

}