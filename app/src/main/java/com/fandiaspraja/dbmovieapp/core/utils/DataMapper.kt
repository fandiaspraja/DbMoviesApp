package com.fandiaspraja.dbmovieapp.core.utils

import com.fandiaspraja.dbmovieapp.core.data.source.local.entity.MovieEntity
import com.fandiaspraja.dbmovieapp.core.data.source.local.entity.TourismEntity
import com.fandiaspraja.dbmovieapp.core.data.source.remote.response.TourismResponse
import com.fandiaspraja.dbmovieapp.core.domain.model.Movie
import com.fandiaspraja.dbmovieapp.core.domain.model.Tourism
import com.fandiaspraja.moviedbapp.core.data.source.remote.response.MovieItem

object DataMapper {

    fun mapResponsesToEntitiesMovie(input: List<MovieItem>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movies = MovieEntity(
                overview = it.overview,
                originalLanguage = it.original_language,
                originalTitle = it.original_title,
                video = it.video,
                title = it.title,
                posterPath = it.poster_path,
                backdropPath = it.backdrop_path,
                releaseDate = it.release_date,
                popularity = it.popularity,
                voteAverage = it.vote_average,
                id = it.id,
                adult = it.adult,
                voteCount = it.vote_count,
                isFavorite = false
            )
            movieList.add(movies)
        }
        return movieList
    }

    fun mapEntitiesToDomainMovie(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                video = it.video,
                title = it.title,
                genreIds = null,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                id = it.id,
                adult = it.adult,
                voteCount = it.voteCount,
                isFavorite = false
            )
        }

    fun mapDomainToEntityMovie(it: Movie) = MovieEntity(
        overview = it.overview,
        originalLanguage = it.originalLanguage,
        originalTitle = it.originalTitle,
        video = it.video,
        title = it.title,
        posterPath = it.posterPath,
        backdropPath = it.backdropPath,
        releaseDate = it.releaseDate,
        popularity = it.popularity,
        voteAverage = it.voteAverage,
        id = it.id,
        adult = it.adult,
        voteCount = it.voteCount,
        isFavorite = false
    )

    fun mapResponsesToEntities(input: List<TourismResponse>): List<TourismEntity> {
        val tourismList = ArrayList<TourismEntity>()
        input.map {
            val tourism = TourismEntity(
                tourismId = it.id,
                description = it.description,
                name = it.name,
                address = it.address,
                latitude = it.latitude,
                longitude = it.longitude,
                like = it.like,
                image = it.image,
                isFavorite = false
            )
            tourismList.add(tourism)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<TourismEntity>): List<Tourism> =
            input.map {
                Tourism(
                        tourismId = it.tourismId,
                        description = it.description,
                        name = it.name,
                        address = it.address,
                        latitude = it.latitude,
                        longitude = it.longitude,
                        like = it.like,
                        image = it.image,
                        isFavorite = it.isFavorite
                )
            }
    fun mapDomainToEntity(input: Tourism) = TourismEntity(
            tourismId = input.tourismId,
            description = input.description,
            name = input.name,
            address = input.address,
            latitude = input.latitude,
            longitude = input.longitude,
            like = input.like,
            image = input.image,
            isFavorite = input.isFavorite
    )
}