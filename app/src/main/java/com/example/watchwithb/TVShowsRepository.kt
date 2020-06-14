package com.example.watchwithb

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TVShowsRepository {
    private val api: TVShowAPI

    init{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(TVShowAPI::class.java)
    }

    fun getPopularTVShows(
            page: Int =  1,
            onSuccess:(tvshows: List<TVShow>) -> Unit,
            onError: () -> Unit
        ){
            api.getPopularTVShows(page = page)
                .enqueue(object: Callback<GetTVShowResponse> {
                    override fun onResponse(
                        call: Call<GetTVShowResponse>,
                        response: Response<GetTVShowResponse>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                onSuccess.invoke(responseBody.tvshows)
                            } else {
                                onError.invoke()
                            }
                        } else {
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<GetTVShowResponse>, t: Throwable) {
                        onError.invoke()
                    }
                })
    }

    fun getTopRatedTVShows(
        page: Int =  1,
        onSuccess:(tvshows: List<TVShow>) -> Unit,
        onError: () -> Unit
    ){
        api.getTopRatedTVShows(page = page)
            .enqueue(object: Callback<GetTVShowResponse> {
                override fun onResponse(
                    call: Call<GetTVShowResponse>,
                    response: Response<GetTVShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvshows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTVShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getOnAirTVShows(
        page: Int =  1,
        onSuccess:(tvshows: List<TVShow>) -> Unit,
        onError: () -> Unit
    ){
        api.getOnAirTVShows(page = page)
            .enqueue(object: Callback<GetTVShowResponse> {
                override fun onResponse(
                    call: Call<GetTVShowResponse>,
                    response: Response<GetTVShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvshows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTVShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getAiringTodayTVShows(
        page: Int =  1,
        onSuccess:(tvshows: List<TVShow>) -> Unit,
        onError: () -> Unit
    ){
        api.getAiringTodayTVShows(page = page)
            .enqueue(object: Callback<GetTVShowResponse> {
                override fun onResponse(
                    call: Call<GetTVShowResponse>,
                    response: Response<GetTVShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvshows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTVShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}