package com.example.project1


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
    var recyclerView: RecyclerView? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var recyclerViewImg: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getService()?.getNowPlaying(API_KEY)?.enqueue(object : Callback<NowPlaying?> {
            override fun onFailure(call: Call<NowPlaying?>?, t: Throwable?) {
                var a = 0
            }

            override fun onResponse(call: Call<NowPlaying?>?, response: Response<NowPlaying?>) {
                adapter?.setList(response.body()?.results as ArrayList<Movie>)
                adapterImg?.setList1(response.body()?.results as ArrayList<Movie>)

            }
        })
//        recyclerViewImg = findViewById(R.id.rcView)
        recyclerView = findViewById(R.id.rcView2)
        movieList = ArrayList()
        setupList()
        setupImg()
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout?.setOnRefreshListener {
            Toast.makeText(this, "Refreshed", Toast.LENGTH_SHORT).show()
            swipeRefreshLayout?.isRefreshing = false
            this.getString(R.string.movie_title_str)
        }


    }

    private fun setupList() {
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = mLayoutManager
        adapter = MovieAdapter(this, movieList ?: ArrayList()) { movie ->
            val bundle = Bundle()
            bundle.putParcelable("movie", movie)
            val trailer = Intent(this, TrailerMovie::class.java)
            trailer.putExtras(bundle)
            startActivity(trailer)
        }
        recyclerView?.adapter = adapter
    }

    private fun setupImg() {
        val mLayoutManager1: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerViewImg?.layoutManager = mLayoutManager1
        adapterImg = ImgAdapter(this, movieList ?: ArrayList())
    }


}