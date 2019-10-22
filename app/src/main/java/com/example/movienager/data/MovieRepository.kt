package com.example.movienager.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.movienager.network.RetrofitInitializer
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MovieRepository private constructor(private val movieDao: MovieDao) {

    fun getAll() = movieDao.getAll()

    fun getSearchCache() = movieDao.getSearchCache()


    fun fetchMovies(searchQuery: String){
        RetrofitInitializer().movieService().searchMovies(searchQuery).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>){
                val response : SearchResponse? = response.body()
                if(response == null) return this.onFailure(call, Throwable("null?"))

                GlobalScope.launch {
                    movieDao.clearCache()

                    val movies = response.Search
                    if(movies != null){
                        movies.map {
                            it.createdAt = Calendar.getInstance()
                            it.isCache = true
                        }

                        movieDao.insertAll(movies)
                    }
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e("api error", t.toString())
            }
        })
    }

    fun getByImdbId(imdbId: String) : LiveData<Movie>{
        fetchMovieByImdbId(imdbId)
        val movie = movieDao.getByImdbId(imdbId)
        return movie
    }

    fun fetchMovieByImdbId(imdbId: String){
        RetrofitInitializer().movieService().searchMovieByImdbId(imdbId).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val response : Movie? = response.body()
                if(response == null) return this.onFailure(call, Throwable("null?"))

                response.createdAt = Calendar.getInstance()
                response.isCache = false

                GlobalScope.launch {
                    movieDao.insert(response)
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("api error", t.toString())
            }
        })
    }

    companion object {

        //Singleton
        @Volatile private var instance : MovieRepository? = null

        fun getInstance(movieDao: MovieDao) =
            instance ?: synchronized(this){
                instance
                    ?: MovieRepository(movieDao).also { instance = it }
            }
    }
}