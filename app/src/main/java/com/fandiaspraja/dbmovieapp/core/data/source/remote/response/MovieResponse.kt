package com.fandiaspraja.moviedbapp.core.data.source.remote.response

data class MovieResponse(
    val page: Int,
    val totalPages: Int,
    val results: List<MovieItem>,
    val totalResults: Int
)
