package com.example.project1

import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.project1.api.API_KEY
import com.example.project1.api.API_KEY_YOUTUBE
import com.example.project1.api.AVER_RATING
import com.example.project1.api.RetrofitClient.Companion.getService
import com.example.project1.entity.Videos
import com.example.project1.model.Movie
import com.example.project1.model.Trailer
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrailerMovie : YouTubeBaseActivity() {

    var img_poster_trailer: ImageView? = null
    var tv_title: TextView? = null
    var tv_release_date: TextView? = null
    var tv_trailer_release_date: TextView? = null
    var rating_bar: RatingBar? = null
    var tv_overview_trailer: TextView? = null
    var youtube_player: YouTubePlayerView? = null
    var tv_rating: TextView? = null
    val trailerMovie: List<Trailer>? = null
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer)
        val bundle = intent.extras
        movie = bundle?.getParcelable<Parcelable>("movie") as Movie?

        img_poster_trailer = findViewById(R.id.img_poster_detail)
        tv_title = findViewById(R.id.tv_title)
        tv_release_date = findViewById(R.id.tv_release_date)
        tv_trailer_release_date = findViewById(R.id.tv_detail_release_date)
        rating_bar = findViewById(R.id.rating_bar)
        tv_overview_trailer = findViewById(R.id.tv_overview_detail)
        youtube_player = findViewById(R.id.youtube_player)
        tv_rating = findViewById(R.id.tv_rating)

        tv_title!!.text = movie!!.title
        rating_bar!!.rating = movie!!.voteAverage.toFloat()
        rating_bar!!.setIsIndicator(true)
        tv_trailer_release_date!!.text = movie!!.releaseDate
        tv_overview_trailer!!.text = movie!!.overview
        tv_rating!!.text = movie!!.voteAverage.toString()
        Glide.with(this).load(movie!!.posterURL()).apply(RequestOptions()
            .fitCenter())
            .into(img_poster_trailer!!)



        getService()?.getVideo(movie!!.id,API_KEY)?.enqueue(object : Callback<Videos?>{
            override fun onFailure(call: Call<Videos?>?, t: Throwable?) {
                var a = 0
            }

            override fun onResponse(call: Call<Videos?>?, response: Response<Videos?>?) {
                youtubePlayer(response?.body()?.results?.get(0)?.id)
            }

        })

    }
    private fun youtubePlayer(id: String?) {
        // todo BASE_YOUTUBE_URL xem thử cách dùng link này của github leouong đi e
        youtube_player!!.initialize(API_KEY_YOUTUBE, object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                if (movie!!.voteAverage >= AVER_RATING){
                    p1!!.loadVideo(id)
                    p1!!.setShowFullscreenButton(false)
                }else p1!!.cueVideo(id)

            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {

            }
        })
    }


}



