package com.example.gotren.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.gotren.databinding.ItemTrendingListBinding

import com.example.trendinglib.model.TrendingListResponseItem

class MainAdapter(
    private val context: Context,
    private val trendingListResponse: ArrayList<TrendingListResponseItem>,

) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemTrendingListBinding.inflate( LayoutInflater.from(context),parent,false)

        return ViewHolder(
                binding
        )
    }

    override fun getItemCount(): Int {
        return trendingListResponse.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = trendingListResponse[position]
        holder.title.text = data.author
        holder.name.text = data.name

       holder.imageView.load(data.avatar){
           transformations(CircleCropTransformation())
       }



    }

    class ViewHolder(itemView:  ItemTrendingListBinding) : RecyclerView.ViewHolder(itemView.root) {
        val imageView = itemView.imageView
        val title = itemView.title
        val name = itemView.name

    }

}