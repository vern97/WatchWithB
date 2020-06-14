package com.example.watchwithb

import com.google.gson.annotations.SerializedName

data class GetTVShowResponse (
    @SerializedName("page") val page: Int,
    @SerializedName("results") val tvshows: List<TVShow>,
    @SerializedName("total_pages") val pages: Int
)