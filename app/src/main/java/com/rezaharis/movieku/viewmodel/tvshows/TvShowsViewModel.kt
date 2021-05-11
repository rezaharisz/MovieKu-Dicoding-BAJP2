package com.rezaharis.movieku.viewmodel.tvshows

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rezaharis.movieku.data.source.MovieKuRepository
import com.rezaharis.movieku.data.source.local.entity.DataTvShows

class TvShowsViewModel(private val movieKuRepository: MovieKuRepository): ViewModel() {
    fun getTvShows(): MutableLiveData<ArrayList<DataTvShows>> = movieKuRepository.getTvShows()
}