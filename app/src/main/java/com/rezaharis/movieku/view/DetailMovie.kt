package com.rezaharis.movieku.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.rezaharis.movieku.BuildConfig
import com.rezaharis.movieku.databinding.ActivityDetailMovieBinding
import com.rezaharis.movieku.data.source.local.entity.DataMovie
import com.rezaharis.movieku.viewmodel.movie.MovieDetailViewModel
import com.rezaharis.movieku.viewmodel.ViewModelFactory

class DetailMovie : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    companion object{
        const val MOVIE = "movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }

        val factory = ViewModelFactory.getInstance()
        movieDetailViewModel = ViewModelProvider(this, factory)[MovieDetailViewModel::class.java]

        movieDetailViewModel.getMovieId(intent.getIntExtra(MOVIE, 0)).observe(this, {
            getMovie(it)
            if (it != null){
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

    private fun getMovie(movie: DataMovie){
        Glide.with(this)
                .load(BuildConfig.BASE_IMAGE + movie.poster)
                .override(250, 320)
                .into(binding.ivPoster)
        binding.tvMovie.text = movie.movieName
        binding.movieRelease.text = movie.releasedate
        binding.movieRate.text = movie.rate.toString()
        binding.movieVotecount.text = movie.votecount.toString()
        binding.tvDes.text = movie.description
    }
}