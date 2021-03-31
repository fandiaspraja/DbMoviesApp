package com.fandiaspraja.dbmovieapp.ui.home

import com.fandiaspraja.dbmovieapp.core.data.Resource
import com.fandiaspraja.dbmovieapp.core.domain.model.Movie
import com.fandiaspraja.dbmovieapp.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class HomesViewModelTest {

    lateinit var homesViewModel: HomesViewModel
    lateinit var movieUseCase: MovieUseCase

    @Mock
    private lateinit var movieList: List<Movie>

    @Before
    fun setUp() {
        movieUseCase = mock(MovieUseCase::class.java)
        homesViewModel = HomesViewModel(movieUseCase)
    }

    @Test
    fun getBannerMovie() {
        runBlocking {
            val flow = flow {
                emit(Resource.Loading(movieList))
                delay(10)
                emit(Resource.Success(movieList))
            }

            `when`(movieUseCase.getBanners()).thenReturn(flow)
//            `when`(movieList.get(0)).thenReturn(ChocolateModel("Pavneet", 1))
            val liveData = homesViewModel
            liveData.bannerMovie

            assertNotNull(liveData)

//            verify(mockObserver).onChanged(captor.capture())
//            assertEquals(true, captor.value.loading)
//            coroutineScope.advanceTimeBy(10)
//            verify(mockObserver, times(2)).onChanged(captor.capture())
//            assertEquals("Pavneet", captor.value.data[0].name)
        }
//        val bannerMovie = homesViewModel.bannerMovie
//        assertNull(bannerMovie)
    }

    @Test
    fun getPopularMovie() {
        val popularMovie = homesViewModel.popularMovie
        assertNull(popularMovie)
    }

    @Test
    fun getCoomingMovie() {
    }
}