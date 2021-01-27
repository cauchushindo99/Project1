package com.example.project1



import android.content.Intent
import android.os.Bundle
import android.text.Html


import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.example.project1.adapter.ImgAdapter

import com.example.project1.adapter.MovieAdapter
import com.example.project1.api.API_KEY
import com.example.project1.api.RetrofitClient.Companion.getService
import com.example.project1.model.Movie
import com.example.project1.model.NowPlaying
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    var movieList: ArrayList<Movie>? = null
    var adapter: MovieAdapter? = null
    var adapterImg: ImgAdapter? = null
    private var recyclerView: RecyclerView? = null
    var recyclerViewImg: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getService()?.getNowPlaying(API_KEY)?.enqueue(object :Callback<NowPlaying?>{
            override fun onFailure(call: Call<NowPlaying?>?, t: Throwable?) {
                var a = 0
            }
            override fun onResponse(call: Call<NowPlaying?>?, response: Response<NowPlaying?>) {
                adapter?.setList(response.body()?.results as ArrayList<Movie>)
                adapterImg?.setList1(response.body()?.results as ArrayList<Movie>)

            }
        })
        recyclerViewImg = findViewById(R.id.rc_view)
        recyclerView = findViewById(R.id.rc_view2)
        movieList = ArrayList()
        setupList()
        setupImg()



    }
    private fun setupList(){
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = mLayoutManager
        adapter = MovieAdapter(this, movieList?: ArrayList()){ movie ->
            val bundle = Bundle()
            bundle.putParcelable("movie", movie)
            val trailer = Intent(this, TrailerMovie::class.java)
            trailer.putExtras(bundle)
            startActivity(trailer)
        }
        recyclerView?.adapter = adapter
    }
    private fun setupImg(){
        val mLayoutManager1: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerViewImg?.layoutManager = mLayoutManager1
        adapterImg = ImgAdapter(this, movieList?: ArrayList())
    }





}