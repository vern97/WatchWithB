package com.example.watchwithb

import com.google.gson.annotations.SerializedName

data class TVShow(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("first_air_date") val releaseDate: String
)