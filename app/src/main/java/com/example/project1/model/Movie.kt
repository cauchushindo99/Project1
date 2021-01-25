package com.example.project1.model

import android.os.Parcelable

import com.example.project1.api.BASE_IMAGES_URL

import com.example.project1.api.POSTER_SIZE
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.lang.StringBuilder
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Movie(
    @Expose
    @SerializedName("adult")
    var adult: Boolean = false,
    @Expose
    @SerializedName("backdrop_path")
    var backdropPath: String = "",
    @Expose
    @SerializedName("genre_ids")
    var genreIds: List<Int> = listOf(),
    @Expose
    @SerializedName("id")
    var id: Int = 0,
    @Expose
    @SerializedName("original_language")
    var originalLanguage: String = "",
    @Expose
    @SerializedName("original_title")
    var originalTitle: String = "",
    @Expose
    @SerializedName("overview")
    var overview: String = "",
    @Expose
    @SerializedName("popularity")
    var popularity: Double = 0.0,
    @Expose
    @SerializedName("poster_path")
    var posterPath: String = "",
    @Expose
    @SerializedName("release_date")
    var releaseDate: String = "",
    @Expose
    @SerializedName("title")
    var title: String?,
    @Expose
    @SerializedName("video")
    var video: Boolean = false,
    @Expose
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,
    @Expose
    @SerializedName("vote_count")
    var voteCount: Long = 0L

): Parcelable {
    fun posterURL(): String? {
        if ( posterPath == null) return null
        val stringBuilder = StringBuilder()
        stringBuilder.append(BASE_IMAGES_URL)
       stringBuilder.append(POSTER_SIZE)
        stringBuilder.append(posterPath)
        return stringBuilder.toString()
    }
//    fun backdropUrl(): String? {
//        if (backdropPath == null) return null
//        val stringBuilder = StringBuilder()
//        stringBuilder.append(BASE_IMAGES_URL)
//        stringBuilder.append(BACKDROP_SIZE)
//        stringBuilder.append(backdropPath)
//        return stringBuilder.toString()
//    }

}


