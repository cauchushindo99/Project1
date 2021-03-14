package com.example.project1

import android.os.Bundle
import android.os.Parcelable
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.project1.api.API_KEY
import com.example.project1.api.API_KEY_YOUTUBE
import com.example.project1.api.AVER_RATING
import com.example.project1.api.RetrofitClient.Companion.getService
import com.example.project1.model.Videos
import com.example.project1.model.Movie
import com.example.project1.model.Trailer
import com.example.project1.model.VideoListDTO
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrailerMovie : YouTubeBaseActivity() {

    var imgPosterTrailer: ImageView? = null
    var tvTitle: TextView? = null
    var tvReleaseDate: TextView? = null
    var tvTrailerReleaseDate: TextView? = null
    var ratingBar: RatingBar? = null
    var tvOverviewTrailer: TextView? = null
    var youtubePlayer: YouTubePlayerView? = null
    var tvRating: TextView? = null
    val trailerMovie: List<Trailer>? = null
    var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer)
        val bundle = intent.extras
        movie = bundle?.getParcelable<Parcelable>("movie") as Movie?
        imgPosterTrailer = findViewById(R.id.imgPosterDetail)
        tvTitle = findViewById(R.id.tvTitle)
        tvReleaseDate = findViewById(R.id.tvReleaseDate)
        tvTrailerReleaseDate = findViewById(R.id.tvDetailReleaseDate)
        ratingBar = findViewById(R.id.ratingBar)
        tvOverviewTrailer = findViewById(R.id.tvOverviewDetail)
        youtubePlayer = findViewById(R.id.youtubePlayer)
        tvRating = findViewById(R.id.tvRating)
        tvTitle?.text = movie?.title
        ratingBar?.rating = movie!!.voteAverage.toFloat()
        ratingBar?.setIsIndicator(true)
        tvTrailerReleaseDate?.text = movie?.releaseDate
        tvOverviewTrailer?.text = movie?.overview
        tvRating?.text = movie?.voteAverage.toString()
        Glide.with(this).load(movie?.posterURL()).apply(RequestOptions()
            .fitCenter())
            .into(imgPosterTrailer?: return)
        getService()?.getVideo(movie?.id?: 0,API_KEY)?.enqueue(object : Callback<Videos?>{
            override fun onFailure(call: Call<Videos?>?, t: Throwable?) {
                var a = 0
            }
            override fun onResponse(call: Call<Videos?>?, response: Response<Videos?>?) {
                youtubePlayer(response?.body()?.results?.get(0)?.key)
            }
        })
    }
    private fun youtubePlayer(key: String?) {
        youtubePlayer?.initialize(API_KEY_YOUTUBE, object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                if (movie!!.voteAverage >= AVER_RATING){
                    p1?.loadVideo(key)
                    p1?.setShowFullscreenButton(true)
                }else p1?.cueVideo(key)
            }
            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                var a = 0
            }
        })
    }


}



