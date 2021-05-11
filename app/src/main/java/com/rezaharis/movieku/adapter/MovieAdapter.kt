package com.rezaharis.movieku.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rezaharis.movieku.BuildConfig
import com.rezaharis.movieku.view.DetailMovie
import com.rezaharis.movieku.R
import com.rezaharis.movieku.databinding.ItemMovieBinding
import com.rezaharis.movieku.data.source.local.entity.DataMovie
import com.rezaharis.movieku.view.DetailMovie.Companion.MOVIE

class MovieAdapter(private val listMovie: ArrayList<DataMovie>): RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    fun setData(movie: ArrayList<DataMovie>){
        listMovie.addAll(movie)
        notifyDataSetChanged()
    }

    inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieBinding.bind(itemView)

        fun bind(dataMovie: DataMovie){
            Glide.with(itemView)
                .load(BuildConfig.BASE_IMAGE + dataMovie.poster)
                .override(150,220)
                .into(binding.ivImage)
            binding.tvMovie.text = dataMovie.movieName

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailMovie::class.java)
                intent.putExtra(MOVIE, dataMovie.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }
}