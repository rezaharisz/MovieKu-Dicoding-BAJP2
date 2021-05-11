package com.rezaharis.movieku.viewmodel.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rezaharis.movieku.data.source.MovieKuRepository
import com.rezaharis.movieku.data.source.local.entity.DataMovie

class MovieViewModel(private val movieKuRepository: MovieKuRepository): ViewModel() {
    fun getMovies(): MutableLiveData<ArrayList<DataMovie>> = movieKuRepository.getMovies()
}