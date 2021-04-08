package com.fandiaspraja.dbmovieapp.ui.home

import android.content.ClipData
import com.fandiaspraja.dbmovieapp.core.data.MovieRepository
import com.fandiaspraja.dbmovieapp.core.data.Resource
import com.fandiaspraja.dbmovieapp.core.domain.model.Movie
import com.fandiaspraja.dbmovieapp.core.domain.usecase.MovieInteractor
import com.fandiaspraja.dbmovieapp.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class HomesViewModelTest {

//    lateinit var homesViewModel: HomesViewModel
//    @Mock
//    lateinit var movieUseCase: MovieUseCase
//    lateinit var movieInteractor: MovieInteractor
//    lateinit var movieRepository: MovieRepository

//    @Mock
//    private lateinit var movieList: List<Movie>

    @Before
    fun setUp() {

//        movieRepository = mock(MovieRepository::class.java)
//        movieInteractor = mock(MovieInteractor(movieRepository)::class.java)
//        movieUseCase = mock(MovieUseCase::class.java)
//        homesViewModel = mock(HomesViewModel(movieUseCase)::class.java)
    }

    @Test
    fun getBannerMovie() {
//        runBlocking {
//            val flow = flow {
//                emit(Resource.Loading(movieList))
//                delay(10)
//                emit(Resource.Success(movieList))
//            }

//            val bannerMovie = homesViewModel.bannerMovie
//            assertNotNull(bannerMovie)

//        }
    }

    @Test
    fun getPopularMovie() {
//        val popularMovie = homesViewModel.popularMovie
//        assertNull(popularMovie)
    }

    @Test
    fun getCoomingMovie() {
    }
}