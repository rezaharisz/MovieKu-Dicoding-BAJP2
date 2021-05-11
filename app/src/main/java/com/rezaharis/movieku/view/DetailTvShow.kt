package com.rezaharis.movieku.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.rezaharis.movieku.BuildConfig
import com.rezaharis.movieku.databinding.ActivityDetailTvShowBinding
import com.rezaharis.movieku.data.source.local.entity.DataTvShows
import com.rezaharis.movieku.viewmodel.tvshows.TvShowsDetailViewModel
import com.rezaharis.movieku.viewmodel.ViewModelFactory

class DetailTvShow : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTvShowBinding
    private lateinit var tvShowsDetailViewModel: TvShowsDetailViewModel

    companion object{
        const val TV_SH0WS = "tv_shows"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }

        val factory = ViewModelFactory.getInstance()
        tvShowsDetailViewModel = ViewModelProvider(this, factory)[TvShowsDetailViewModel::class.java]

        tvShowsDetailViewModel.getTvShowsId(intent.getIntExtra(TV_SH0WS, 0)).observe(this, {
            getTvShows(it)

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

    private fun getTvShows(tvShows: DataTvShows){
        Glide.with(this)
                .load(BuildConfig.BASE_IMAGE + tvShows.poster)
                .override(250, 320)
                .into(binding.ivPosterTvshows)
        binding.tvshowsRelease.text = tvShows.releasedate
        binding.tvshowsRate.text = tvShows.rate.toString()
        binding.tvshowsVotecount.text = tvShows.votecount.toString()
        binding.tvDesTvshows.text = tvShows.description
        binding.tvTvshows.text = tvShows.tvShowsName
    }
}