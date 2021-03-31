package com.fandiaspraja.moviedbapp.core.data.source.remote.response

data class MovieItem(
	val overview: String,
	val original_language: String,
	val original_title: String,
	val video: Boolean,
	val title: String,
	val genre_ids: List<Int>,
	val poster_path: String,
	val backdrop_path: String,
	val release_date: String,
	val popularity: Double,
	val vote_average: Double,
	val id: Int,
	val adult: Boolean,
	val vote_count: Int
)
