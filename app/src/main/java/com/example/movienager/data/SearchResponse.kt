package com.example.movienager.data

data class SearchResponse (
    val totalResults: Int,
    val Response: Boolean,
    val Search: List<Movie>
)