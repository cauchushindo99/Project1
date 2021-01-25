package com.example.project1.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NowPlaying(
    @Expose
    @SerializedName("page")
    var page: Int = 0,
    @Expose
    @SerializedName("results")
    var results: List<Movie> = listOf()

)