package com.rezaharis.movieku.data.source.local.entity

import com.google.gson.annotations.SerializedName

data class DataTvShows(

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("poster_path")
        val poster: String? = null,

        @field:SerializedName("original_name")
        val tvShowsName: String? = null,

        @field:SerializedName("overview")
        val description: String? = null,

        @field:SerializedName("first_air_date")
        val releasedate: String? = null,

        @field:SerializedName("vote_average")
        val rate: Double? = null,

        @field:SerializedName("vote_count")
        val votecount: Int? = null
)