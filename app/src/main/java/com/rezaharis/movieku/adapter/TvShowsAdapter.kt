package com.rezaharis.movieku.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rezaharis.movieku.BuildConfig
import com.rezaharis.movieku.R
import com.rezaharis.movieku.databinding.ItemTvshowBinding
import com.rezaharis.movieku.data.source.local.entity.DataTvShows
import com.rezaharis.movieku.view.DetailTvShow
import com.rezaharis.movieku.view.DetailTvShow.Companion.TV_SH0WS

class TvShowsAdapter(private val listTvShows: ArrayList<DataTvShows>): RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {

    fun setData(tvShows: List<DataTvShows>){
        listTvShows.addAll(tvShows)
        notifyDataSetChanged()
    }

    inner class TvShowsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTvshowBinding.bind(itemView)

        fun bind(dataTvShows: DataTvShows){
            Glide.with(itemView)
                    .load(BuildConfig.BASE_IMAGE + dataTvShows.poster)
                    .override(150,220)
                    .into(binding.ivImageTvshows)
            binding.tvTvshows.text = dataTvShows.tvShowsName

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailTvShow::class.java)
                intent.putExtra(TV_SH0WS, dataTvShows.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_tvshow,parent, false)
        return TvShowsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        holder.bind(listTvShows[position])
    }

    override fun getItemCount(): Int {
        return listTvShows.size
    }
}