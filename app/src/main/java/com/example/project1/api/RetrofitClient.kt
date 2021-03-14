package com.example.project1.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "158823ee8cf8627ff81d262a409a53a0"
const val API_KEY_IMG = "158823ee8cf8627ff81d262a409a53a0"
const val BASE_IMAGES_URL = "http://image.tmdb.org/t/p/"
const val POSTER_SIZE = "w200"
const val BASE_YOUTUBE_URL = "https://www.youtube.com/watch?v="
const val API_KEY_YOUTUBE = "AIzaSyA1vrnvlc9Q9D9DkSVwJHt7yZOyA1RcOws"
const val AVER_RATING = 10.0

class RetrofitClient {
    companion object {
        var retrofit: Retrofit? = null
        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
        fun getService(): MovieService?{
            return getClient()?.create(MovieService::class.java)
        }
    }
}