package com.example.movienager.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val imdbID: String,
    val Title: String,
    val Year: String,
    val Poster: String,
    val Type: String,
    val Watched: Boolean,

    val Metascore: Integer?,
    val imdbRating: String?,
    val imdbVotes: String?,
    val Runtime: String?,
    val Genre: String?,
    val Director: String?,
    val Writer: String?,
    val Actors: String?,
    val Plot: String?,

    var isCache: Boolean,

    @ColumnInfo(name = "created_at")
    var createdAt: Calendar
)