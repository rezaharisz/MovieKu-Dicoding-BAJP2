package com.rezaharis.movieku.di

import com.rezaharis.movieku.data.source.remote.sources.RemoteDataSource
import com.rezaharis.movieku.data.source.MovieKuRepository

object Injection {
    fun getRepositories(): MovieKuRepository{
        val movieKuDataSource = RemoteDataSource()

        return MovieKuRepository.getInstance(movieKuDataSource)
    }
}