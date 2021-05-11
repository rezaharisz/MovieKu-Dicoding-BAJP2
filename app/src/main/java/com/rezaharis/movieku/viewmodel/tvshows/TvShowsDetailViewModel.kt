package com.rezaharis.movieku.viewmodel.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rezaharis.movieku.data.source.MovieKuRepository
import com.rezaharis.movieku.data.source.local.entity.DataTvShows

class TvShowsDetailViewModel(private val movieKuRepository: MovieKuRepository): ViewModel() {
    fun getTvShowsId(id: Int): LiveData<DataTvShows> = movieKuRepository.getTvShowsId(id)
}