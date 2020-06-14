package com.example.watchwithb

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TVShowAPI {
    @GET("tv/popular")
    fun getPopularTVShows(
        @Query("api_key") apiKey: String = "c7e0742fd5856e711158175488d7d72a",
        @Query("page") page: Int
    ): Call<GetTVShowResponse>

    @GET("tv/top_rated")
    fun getTopRatedTVShows(
        @Query("api_key") apiKey: String = "c7e0742fd5856e711158175488d7d72a",
        @Query("page") page: Int
    ): Call<GetTVShowResponse>

    @GET("tv/on_the_air")
    fun getOnAirTVShows(
        @Query("api_key") apiKey: String = "c7e0742fd5856e711158175488d7d72a",
        @Query("page") page: Int
    ): Call<GetTVShowResponse>

    @GET("tv/airing_today")
    fun getAiringTodayTVShows(
        @Query("api_key") apiKey: String = "c7e0742fd5856e711158175488d7d72a",
        @Query("page") page: Int
    ): Call<GetTVShowResponse>
}