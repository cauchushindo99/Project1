package com.example.project1.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.R
import com.example.project1.model.Movie

class MovieAdapter(private val mContext: Context, private var mList: List<Movie>?, private val onClickItem: (Movie?) -> Unit) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    fun setList(list: List<Movie> ) {
        mList = list
        notifyDataSetChanged()
    }


      inner class MovieViewHolder (itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{

          val tvName: TextView= itemView.findViewById(R.id.name)
          val tvOverview: TextView= itemView.findViewById(R.id.overview)
          val imgPoster: ImageView= itemView.findViewById(R.id.poster)

          override fun onClick(view: View){
           onClickItem(mList?.get(adapterPosition))
          }
          init {
              itemView.setOnClickListener(this)

          }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
       return MovieViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.activity_item, parent,false)
       )
    }

    override fun getItemCount(): Int {
       return mList?.size?:0
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.tvName.text = mList?.get(position)?.title
        holder.tvOverview.text = mList?.get(position)?.overview
        Glide.with(mContext).load(mList?.get(position)?.posterURL()).apply(RequestOptions()
            .fitCenter())
            .into(holder.imgPoster)
//        holder.imgPoster.setImageResource()
//        mList?.get(position)?.imageItem?.let { setImgItemRecycle(holder.imgRecyclerView, it) }

    }
//    private fun setImgItemRecycle(rcView: RecyclerView, imgItem: List<Image>){
//        val itemRecycleAdapter = ImgAdapter(mContext, imgItem)
//        rcView.layoutManager = LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false)
//        rcView.adapter = itemRecycleAdapter
//    }

}











