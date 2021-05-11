package com.rezaharis.movieku.data.source.remote.responses

import com.google.gson.annotations.SerializedName
import com.rezaharis.movieku.data.source.local.entity.DataMovie

data class ResponseMovie(
        @field:SerializedName("page")
        val page: Int? = null,

        @field:SerializedName("results")
        val results: ArrayList<DataMovie>? = null,

        @field:SerializedName("total_pages")
        val totalPages: Int? = null,

        @field:SerializedName("total_results")
        val totalResults: Int? = null
)
