package com.example.movienager.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val BASE_URL : String = "http://www.omdbapi.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun movieService(): MovieService = retrofit.create(MovieService::class.java)
}