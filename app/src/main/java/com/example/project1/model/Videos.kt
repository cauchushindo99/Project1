package com.example.project1.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName




data class Videos (
    @Expose
    @SerializedName("id")
    var id: Int = 0,
    @Expose
    @SerializedName("results")
    var results: List<VideoListDTO> = listOf()



)
