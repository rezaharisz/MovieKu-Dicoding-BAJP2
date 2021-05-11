package com.rezaharis.movieku.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rezaharis.movieku.data.source.local.entity.DataMovie
import com.rezaharis.movieku.data.source.local.entity.DataTvShows
import com.rezaharis.movieku.data.source.remote.sources.MovieKuDataSource
import com.rezaharis.movieku.data.source.remote.sources.RemoteDataSource

class FakeMovieKuRepository (private val remoteDataSource: RemoteDataSource): MovieKuDataSource {

    private val dataTvShows = MutableLiveData<DataTvShows>()
    private val dataMovie = MutableLiveData<DataMovie>()

    override fun getMovies(): MutableLiveData<ArrayList<DataMovie>> {
        val listMovieMutable = MutableLiveData<ArrayList<DataMovie>>()

        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponse: ArrayList<DataMovie>) {
                listMovieMutable.postValue(movieResponse)
            }
        })
        return listMovieMutable
    }

    override fun getMovieId(id: Int): LiveData<DataMovie>{
        remoteDataSource.getMoviesId(id, object : RemoteDataSource.LoadMoviesIdCallback {
            override fun onMoviesIdReceived(movieId: DataMovie) {
                dataMovie.postValue(movieId)
            }
        })
        return dataMovie
    }

    override fun getTvShows(): MutableLiveData<ArrayList<DataTvShows>>{
        val listTvShowsMutable = MutableLiveData<ArrayList<DataTvShows>>()

        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowsCallback{
            override fun onAllTvShowsReceived(tvShowsResponse: ArrayList<DataTvShows>) {
                listTvShowsMutable.postValue(tvShowsResponse)
            }
        })
        return listTvShowsMutable
    }

    override fun getTvShowsId(id: Int): LiveData<DataTvShows>{
        remoteDataSource.getTvShowsId(id, object : RemoteDataSource.LoadTvShowsIdCallback{
            override fun onTvShowsIdReceived(tvShowsId: DataTvShows) {
                dataTvShows.postValue(tvShowsId)
            }
        })
        return dataTvShows
    }
}