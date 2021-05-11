package com.rezaharis.movieku.viewmodel.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rezaharis.movieku.data.source.MovieKuRepository
import com.rezaharis.movieku.data.source.local.entity.DataMovie

class MovieDetailViewModel(private val movieKuRepository: MovieKuRepository): ViewModel() {
    fun getMovieId(id: Int): LiveData<DataMovie> = movieKuRepository.getMovieId(id)
}