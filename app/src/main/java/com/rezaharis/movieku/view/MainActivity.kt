package com.rezaharis.movieku.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.rezaharis.movieku.viewmodel.movie.MovieViewModel
import com.rezaharis.movieku.adapter.MovieAdapter
import com.rezaharis.movieku.adapter.TvShowsAdapter
import com.rezaharis.movieku.databinding.ActivityMainBinding
import com.rezaharis.movieku.data.source.local.entity.DataMovie
import com.rezaharis.movieku.data.source.local.entity.DataTvShows
import com.rezaharis.movieku.viewmodel.tvshows.TvShowsViewModel
import com.rezaharis.movieku.viewmodel.ViewModelFactory

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var tvShowsAdapter: TvShowsAdapter
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var tvShowsViewModel: TvShowsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance()
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        tvShowsViewModel = ViewModelProvider(this,factory)[TvShowsViewModel::class.java]

        movieAdapter = MovieAdapter(mutableListOf<DataMovie>() as ArrayList<DataMovie>)
        tvShowsAdapter = TvShowsAdapter(mutableListOf<DataTvShows>() as ArrayList<DataTvShows>)

        movieViewModel.getMovies().observe(this, {listMovie ->
            setMovieAdapter()
            if (listMovie != null){
                movieAdapter.setData(listMovie)
                showLoading(false)
            }
        })

        tvShowsViewModel.getTvShows().observe(this, {listTvShows ->
            setTvShowsAdapter()
            if (listTvShows != null){
                tvShowsAdapter.setData(listTvShows)
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressbar.visibility = View.VISIBLE
        } else{
            binding.progressbar.visibility = View.GONE
        }
    }

    private fun setMovieAdapter(){
        binding.crRview.adapter = movieAdapter
        binding.crRview.setHasFixedSize(true)
        binding.crRview.set3DItem(true)
        binding.crRview.setInfinite(true)
        binding.crRview.setAlpha(true)
        binding.crRview.getCarouselLayoutManager()
        binding.crRview.getSelectedPosition()
        binding.crRview.setHasFixedSize(true)
        movieAdapter.notifyDataSetChanged()
    }

    private fun setTvShowsAdapter(){
        binding.crTvshows.adapter = tvShowsAdapter
        binding.crTvshows.setHasFixedSize(true)
        binding.crTvshows.set3DItem(true)
        binding.crTvshows.setInfinite(true)
        binding.crTvshows.setAlpha(true)
        binding.crTvshows.getCarouselLayoutManager()
        binding.crTvshows.getSelectedPosition()
        binding.crTvshows.setHasFixedSize(true)
        tvShowsAdapter.notifyDataSetChanged()
    }
}