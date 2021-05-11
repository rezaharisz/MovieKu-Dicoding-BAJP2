package com.rezaharis.movieku.data.source.remote.sources

import android.util.Log
import com.rezaharis.movieku.api.ApiCall
import com.rezaharis.movieku.data.source.local.entity.DataMovie
import com.rezaharis.movieku.data.source.local.entity.DataTvShows
import com.rezaharis.movieku.data.source.remote.responses.ResponseMovie
import com.rezaharis.movieku.data.source.remote.responses.ResponseTvShows
import com.rezaharis.movieku.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getMovies(callback: LoadMoviesCallback){
        EspressoIdlingResource.increment()
        val response = ApiCall.movieApiService.getMovie()
        response.enqueue(object : Callback<ResponseMovie>{
            override fun onResponse(call: Call<ResponseMovie>, response: Response<ResponseMovie>) {
                response.body()!!.results?.let { callback.onAllMoviesReceived(it) }

                if (!EspressoIdlingResource.idlingResource.isIdleNow){
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                Log.e("MovieViewModel", "OnFailure: ${t.message.toString()}")

                if (!EspressoIdlingResource.idlingResource.isIdleNow){
                    EspressoIdlingResource.decrement()
                }
            }

        })
    }

    fun getMoviesId(id: Int, callback: LoadMoviesIdCallback){
        EspressoIdlingResource.increment()
        val response = ApiCall.movieApiService.getDetailMovie(id)
        response.enqueue(object : Callback<DataMovie> {
            override fun onResponse(call: Call<DataMovie>, response: Response<DataMovie>) {
                response.body()?.let { callback.onMoviesIdReceived(it) }

                if (!EspressoIdlingResource.idlingResource.isIdleNow){
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<DataMovie>, t: Throwable) {
                Log.e("MovieDetailViewModel", "OnFailure: ${t.message.toString()}")

                if (!EspressoIdlingResource.idlingResource.isIdleNow){
                    EspressoIdlingResource.decrement()
                }
            }

        })
    }

    fun getTvShows(callback: LoadTvShowsCallback){
        EspressoIdlingResource.increment()
        val response = ApiCall.movieApiService.getTvShows()
        response.enqueue(object : Callback<ResponseTvShows>{
            override fun onResponse(call: Call<ResponseTvShows>, response: Response<ResponseTvShows>) {
                response.body()!!.results?.let { callback.onAllTvShowsReceived(it) }

                if (!EspressoIdlingResource.idlingResource.isIdleNow){
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<ResponseTvShows>, t: Throwable) {
                Log.e("TvShowsViewModel", "OnFailure: ${t.message.toString()}")

                if (!EspressoIdlingResource.idlingResource.isIdleNow){
                    EspressoIdlingResource.decrement()
                }
            }

        })
    }

    fun getTvShowsId(id: Int, callback: LoadTvShowsIdCallback){
        EspressoIdlingResource.increment()
        val response = ApiCall.movieApiService.getDetailTvShows(id)
        response.enqueue(object : Callback<DataTvShows>{
            override fun onResponse(call: Call<DataTvShows>, response: Response<DataTvShows>) {
                response.body()?.let { callback.onTvShowsIdReceived(it) }

                if (!EspressoIdlingResource.idlingResource.isIdleNow){
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<DataTvShows>, t: Throwable) {
                Log.e("TvShowsDetailViewModel", "OnFailure: ${t.message.toString()}")

                if (!EspressoIdlingResource.idlingResource.isIdleNow){
                    EspressoIdlingResource.decrement()
                }
            }

        })
    }

    interface LoadMoviesCallback{
        fun onAllMoviesReceived(movieResponse: ArrayList<DataMovie>)
    }

    interface LoadMoviesIdCallback{
        fun onMoviesIdReceived(movieId: DataMovie)
    }

    interface LoadTvShowsCallback{
        fun onAllTvShowsReceived(tvShowsResponse: ArrayList<DataTvShows>)
    }

    interface LoadTvShowsIdCallback{
        fun onTvShowsIdReceived(tvShowsId: DataTvShows)
    }
}