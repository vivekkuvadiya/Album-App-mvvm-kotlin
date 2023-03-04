package com.example.demoalbumapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoalbumapp.R
import com.example.demoalbumapp.databinding.ItemPhotoBinding
import com.example.demoalbumapp.remote.model.Photo
import com.squareup.picasso.Picasso

class PhotosAdapter(private val photos:List<Photo>,var onClickPhoto:((Photo)->Unit)?):RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PhotoViewHolder(ItemPhotoBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.onBind(photos[position],onClickPhoto)
    }

    class PhotoViewHolder(private val binding:ItemPhotoBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(photo: Photo, onClickPhoto: ((Photo) -> Unit)?) {
            Picasso.get().load(photo.thumbnailUrl).placeholder(R.drawable.loading).into(binding.ivThumbnail)
            binding.tvTitle.text = photo.title
            binding.root.setOnClickListener {
                onClickPhoto?.let { it(photo) }
            }
        }
    }
}