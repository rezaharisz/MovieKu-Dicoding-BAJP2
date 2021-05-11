@file:Suppress("DEPRECATION")

package com.rezaharis.movieku.viewmodel.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.rezaharis.movieku.data.source.MovieKuRepository
import com.rezaharis.movieku.data.source.local.entity.DataTvShows
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
class TvShowsDetailViewModelTest{

    private lateinit var tvShowsDetailViewModel: TvShowsDetailViewModel
    private val dummyTvShows = DataDummy.getTvShowsList()[0]
    private val tvShowsId = dummyTvShows.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieKuRepository: MovieKuRepository

    @Mock
    private lateinit var tvShowsObserver: Observer<DataTvShows>

    @Before
    fun setUp(){
        tvShowsDetailViewModel = TvShowsDetailViewModel(movieKuRepository)
        tvShowsId?.let { tvShowsDetailViewModel.getTvShowsId(it) }
    }

    @Test
    fun getTvShowsId(){
        val dataTvShows = MutableLiveData<DataTvShows>()
        dataTvShows.value = dummyTvShows

        `when`(tvShowsId?.let { movieKuRepository.getTvShowsId(it) }).thenReturn(dataTvShows)
        val tvShowsEntities = tvShowsId?.let { tvShowsDetailViewModel.getTvShowsId(it).value }

        assertNotNull(tvShowsEntities)
        assertEquals(dummyTvShows, tvShowsEntities)

        tvShowsId?.let { tvShowsDetailViewModel.getTvShowsId(it).observeForever(tvShowsObserver) }
        verify(tvShowsObserver).onChanged(dummyTvShows)
    }
}