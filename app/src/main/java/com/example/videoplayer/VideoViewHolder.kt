package com.example.videoplayer

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imgThumbnail = itemView.findViewById<ImageView>(R.id.imgThumbnail)
    val txtName = itemView.findViewById<TextView>(R.id.txtName)
    val cardView = itemView.findViewById<CardView>(R.id.main_container)
}