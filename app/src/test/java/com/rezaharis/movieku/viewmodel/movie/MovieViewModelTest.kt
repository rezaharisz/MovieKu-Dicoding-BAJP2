@file:Suppress("DEPRECATION")

package com.rezaharis.movieku.viewmodel.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.rezaharis.movieku.data.source.MovieKuRepository
import com.rezaharis.movieku.data.source.local.entity.DataMovie
import com.rezaharis.movieku.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest{

    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieKuRepository: MovieKuRepository

    @Mock
    private lateinit var moviesObserver: Observer<ArrayList<DataMovie>>

    @Before
    fun setUp(){
        movieViewModel = MovieViewModel(movieKuRepository)
    }

    @Test
    fun getMovies(){
        val dataMovie = MutableLiveData<ArrayList<DataMovie>>()
        dataMovie.value = DataDummy.getMoviesList()

        `when`(movieKuRepository.getMovies()).thenReturn(dataMovie)
        val movieEntities = movieViewModel.getMovies().value
        verify(movieKuRepository).getMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        movieViewModel.getMovies().observeForever(moviesObserver)
        verify(moviesObserver).onChanged(DataDummy.getMoviesList())
    }
}