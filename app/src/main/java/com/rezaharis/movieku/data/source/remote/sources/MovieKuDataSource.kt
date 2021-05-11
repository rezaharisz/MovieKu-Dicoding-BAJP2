package com.rezaharis.movieku.data.source.remote.sources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rezaharis.movieku.data.source.local.entity.DataMovie
import com.rezaharis.movieku.data.source.local.entity.DataTvShows

interface MovieKuDataSource {
    fun getMovies(): MutableLiveData<ArrayList<DataMovie>>
    fun getMovieId(id: Int): LiveData<DataMovie>
    fun getTvShows(): MutableLiveData<ArrayList<DataTvShows>>
    fun getTvShowsId(id: Int): LiveData<DataTvShows>
}