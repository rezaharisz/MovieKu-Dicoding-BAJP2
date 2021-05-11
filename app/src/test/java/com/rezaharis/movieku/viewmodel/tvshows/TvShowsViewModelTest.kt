@file:Suppress("DEPRECATION")

package com.rezaharis.movieku.viewmodel.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.rezaharis.movieku.data.source.MovieKuRepository
import com.rezaharis.movieku.data.source.local.entity.DataTvShows
import com.rezaharis.movieku.utils.DataDummy
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.collections.ArrayList

@RunWith(MockitoJUnitRunner::class)
class TvShowsViewModelTest{

    private lateinit var tvShowsViewModel: TvShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieKuRepository: MovieKuRepository

    @Mock
    private lateinit var tvShowsObserver: Observer<ArrayList<DataTvShows>>

    @Before
    fun setUp(){
        tvShowsViewModel = TvShowsViewModel(movieKuRepository)
    }

    @Test
    fun getTvShows(){
        val dataTvShows = MutableLiveData<ArrayList<DataTvShows>>()
        dataTvShows.value = DataDummy.getTvShowsList()

        Mockito.`when`(movieKuRepository.getTvShows()).thenReturn(dataTvShows)
        val tvShowsEntities = tvShowsViewModel.getTvShows().value
        Mockito.verify(movieKuRepository).getTvShows()
        Assert.assertNotNull(tvShowsEntities)
        Assert.assertEquals(10, tvShowsEntities?.size)

        tvShowsViewModel.getTvShows().observeForever(tvShowsObserver)
        Mockito.verify(tvShowsObserver).onChanged(DataDummy.getTvShowsList())
    }

}