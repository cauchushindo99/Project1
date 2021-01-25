package com.example.project1.api

import com.example.project1.entity.Videos
import com.example.project1.model.Movie
import com.example.project1.model.NowPlaying
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService{
    @GET("movie/now_playing")
    fun getNowPlaying(@Query("api_key") values: String?): Call<NowPlaying?>?

    @GET("movie/{movie_id}/videos")
    fun getVideo(@Path("movie_id") id: Int,
                 @Query("api_key") values: String?): Call<Videos?>?

}