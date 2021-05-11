@file:Suppress("DEPRECATION")

package com.rezaharis.movieku.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rezaharis.movieku.data.source.remote.sources.RemoteDataSource
import com.rezaharis.movieku.utils.DataDummy
import com.rezaharis.movieku.utils.LiveDataTestUtils
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any

@RunWith(MockitoJUnitRunner::class)
class MovieKuRepositoryTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieKuRepository = FakeMovieKuRepository(remote)

    private val getMovies = DataDummy.getMoviesList()
    private val getTvShows = DataDummy.getTvShowsList()

    @Test
    fun getMovies(){
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(getMovies)
            null
        }.`when`(remote).getMovies(any())
        val moviesEntities = LiveDataTestUtils.getValue(movieKuRepository.getMovies())
        verify(remote).getMovies(any())
        assertNotNull(moviesEntities)
        assertEquals(getMovies.size.toLong(), moviesEntities.size.toLong())
    }

    @Test
    fun getMovieId(){
        val movieResponse = getMovies[0]

        doAnswer {
            (it.arguments[1] as RemoteDataSource.LoadMoviesIdCallback)
                .onMoviesIdReceived(movieResponse)
            null
        }.`when`(remote).getMoviesId(eq(movieResponse.id)!!, any())
        val moviesEntities = LiveDataTestUtils.getValue(movieKuRepository.getMovieId(movieResponse.id!!))
        verify(remote).getMoviesId(eq(movieResponse.id)!!, any())
        assertNotNull(moviesEntities)
        assertEquals(movieResponse.id, moviesEntities.id)
    }

    @Test
    fun getTvShowsId(){
        val tvShowsResponse = getTvShows[0]

        doAnswer {
            (it.arguments[1] as RemoteDataSource.LoadTvShowsIdCallback)
                .onTvShowsIdReceived(tvShowsResponse)
            null
        }.`when`(remote).getTvShowsId(eq(tvShowsResponse.id)!!, any())
        val tvShowsEntities = LiveDataTestUtils.getValue(movieKuRepository.getTvShowsId(tvShowsResponse.id!!))
        verify(remote).getTvShowsId(eq(tvShowsResponse.id)!!, any())
        assertNotNull(tvShowsEntities)
        assertEquals(tvShowsResponse.id, tvShowsEntities.id)
    }

    @Test
    fun getTvShows(){
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(getTvShows)
            null
        }.`when`(remote).getTvShows(any())
        val tvShowsEntities = LiveDataTestUtils.getValue(movieKuRepository.getTvShows())
        verify(remote).getTvShows(any())
        assertNotNull(tvShowsEntities)
        assertEquals(getTvShows.size.toLong(), tvShowsEntities.size.toLong())
    }
}