package com.rezaharis.movieku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rezaharis.movieku.data.source.MovieKuRepository
import com.rezaharis.movieku.di.Injection
import com.rezaharis.movieku.viewmodel.movie.MovieDetailViewModel
import com.rezaharis.movieku.viewmodel.movie.MovieViewModel
import com.rezaharis.movieku.viewmodel.tvshows.TvShowsDetailViewModel
import com.rezaharis.movieku.viewmodel.tvshows.TvShowsViewModel

class ViewModelFactory(private val movieKuRepository: MovieKuRepository): ViewModelProvider.NewInstanceFactory() {

    private var id: Int = 0

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
                instance?: synchronized(this){
                    instance?: ViewModelFactory(Injection.getRepositories())
                }

        fun getInstance(id: Int): ViewModelFactory =
                instance?: synchronized(this){
                    instance?: ViewModelFactory(Injection.getRepositories(), id)
                }
    }

    private constructor(movieKuRepository: MovieKuRepository, id: Int):
            this(movieKuRepository){
        this.id = id
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(movieKuRepository) as T
            }

            modelClass.isAssignableFrom(TvShowsViewModel::class.java) -> {
                TvShowsViewModel(movieKuRepository) as T
            }

            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> {
                MovieDetailViewModel(movieKuRepository) as T
            }

            modelClass.isAssignableFrom(TvShowsDetailViewModel::class.java) -> {
                TvShowsDetailViewModel(movieKuRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}