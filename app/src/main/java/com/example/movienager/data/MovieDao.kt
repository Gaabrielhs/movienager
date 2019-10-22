package com.example.movienager.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movies ORDER BY created_at DESC")
    fun getAll() : LiveData<List<Movie>>

    @Query("SELECT * FROM Movies WHERE isCache = 1 ORDER BY created_at DESC")
    fun getSearchCache() : LiveData<List<Movie>>

    @Query("SELECT * FROM Movies WHERE imdbID = :imdbId")
    fun getByImdbId(imdbId: String) : LiveData<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)


    @Query("DELETE FROM Movies WHERE isCache = 1")
    fun clearCache()
}