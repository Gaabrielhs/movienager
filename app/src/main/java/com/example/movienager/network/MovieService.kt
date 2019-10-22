package com.example.movienager.network

import com.example.movienager.data.Movie
import com.example.movienager.data.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/?apiKey=b2e04a28&type=movie")
    fun searchMovies(@Query("s") query: String) : Call<SearchResponse>

    @GET("/?apiKey=b2e04a28&type=movie")
    fun searchMovieByImdbId(@Query("i") imdbId: String) : Call<Movie>
}