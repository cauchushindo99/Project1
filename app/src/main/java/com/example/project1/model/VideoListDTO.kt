package com.example.project1.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideoListDTO (
    @Expose
    @SerializedName("id")
    var id: String = "",
    @Expose
    @SerializedName("iso_639_1")
    var iso6391: String="",
    @Expose
    @SerializedName("iso_3166_1")
    var iso31661: String="",
    @Expose
    @SerializedName("key")
    var key: String="",
    @Expose
    @SerializedName("name")
    var name: String="",
    @Expose
    @SerializedName("site")
    var site: String="",
    @Expose
    @SerializedName("size")
    var size: Int=0,
    @Expose
    @SerializedName("type")
    var type: String=""


)
