package com.example.project1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.project1.R
import com.example.project1.model.Movie

class ImgAdapter(private val mContext: Context, private var mList: MutableList<Movie>?) :
    RecyclerView.Adapter<ImgAdapter.ImageViewHolder>() {
    fun setList1(list: MutableList<Movie> ) {
        mList = list
        notifyDataSetChanged()
    }
   class ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImg: ImageView = itemView.findViewById(R.id.img_movie)
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mList?.size?:0
    }



    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(mContext).load(mList?.get(position)?.posterURL()).apply(
            RequestOptions()
            .fitCenter())
            .into(holder.itemImg)
    }
}