package com.mymind.ui.screens.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mymind.R

class CarouselAdapter(private val carouselDataList: ArrayList<Int>) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_carousel, parent, false)
        return CarouselViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return carouselDataList.size
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.imageViewMood)
        imageView.setImageResource(carouselDataList[position])
    }

    inner class CarouselViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
