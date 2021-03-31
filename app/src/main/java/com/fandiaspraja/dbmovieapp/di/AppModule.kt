package com.fandiaspraja.dbmovieapp.di

import com.fandiaspraja.dbmovieapp.core.domain.usecase.MovieInteractor
import com.fandiaspraja.dbmovieapp.core.domain.usecase.MovieUseCase
import com.fandiaspraja.dbmovieapp.detailmovie.DetailMovieViewModel
import com.fandiaspraja.dbmovieapp.ui.favorite.FavoriteMovieViewModel
import com.fandiaspraja.dbmovieapp.ui.home.HomesViewModel
import com.fandiaspraja.dbmovieapp.ui.popular.PopularViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DetailMovieViewModel(get()) }
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { PopularViewModel(get()) }
    viewModel { HomesViewModel(get()) }
}