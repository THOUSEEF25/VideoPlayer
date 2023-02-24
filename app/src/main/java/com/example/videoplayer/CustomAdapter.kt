package com.example.videoplayer

import android.content.Context
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.io.File

@Suppress("DEPRECATION")
class CustomAdapter : RecyclerView.Adapter<VideoViewHolder> {

    private var context: Context
    private var files : List<File>
    private lateinit var listener: SelectListener

    constructor(context: Context, files: List<File>) : super() {
        this.context = context
        this.files = files
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {

        return VideoViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_list, parent, false))
    }

    override fun getItemCount(): Int {
        return files.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.txtName.text = files[position].name
        holder.txtName.isSelected = true

        val thump: Bitmap? = ThumbnailUtils.createVideoThumbnail(files[position].absolutePath,
        MediaStore.Images.Thumbnails.MINI_KIND)

        holder.imgThumbnail.setImageBitmap(thump)

        holder.cardView.setOnClickListener {

            fun onClick(view : View) {
                listener.onFileClicked(files[position])
            }

        }
    }
}