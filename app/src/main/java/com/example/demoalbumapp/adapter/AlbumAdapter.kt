package com.example.demoalbumapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoalbumapp.R
import com.example.demoalbumapp.databinding.ItemAlbumBinding
import com.example.demoalbumapp.remote.model.Photo

class AlbumAdapter(var onClickPhoto:((Photo)->Unit)?): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private val albums = mutableListOf<List<Photo>>()
    private var expandedPosition = -1
    private var previousExpandedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AlbumViewHolder(ItemAlbumBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount() = albums.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {

        val isExpanded = position == expandedPosition
        holder.binding.rvPhotos.isVisible = isExpanded
        holder.binding.icArrow.setImageResource(if(isExpanded)R.drawable.ic_arrow_up else R.drawable.ic_arrow_down)
        holder.binding.tvAlbums.text = "${position + 1} Album"

        if (isExpanded){
            previousExpandedPosition = holder.adapterPosition
        }

        if (isExpanded){
            holder.binding.rvPhotos.apply {
                adapter = PhotosAdapter(albums[position]){photo ->
                    onClickPhoto?.let { it(photo) }
                }
                layoutManager = LinearLayoutManager(holder.binding.root.context)
            }
        }

        holder.binding.root.setOnClickListener {
            expandedPosition = if (isExpanded)-1 else position
            notifyItemChanged(previousExpandedPosition);
            notifyItemChanged(position);
        }
    }

    fun submitList(albums:List<List<Photo>>){
        this.albums.clear()
        this.albums.addAll(albums)
        notifyDataSetChanged()
    }

    class AlbumViewHolder(val binding:ItemAlbumBinding):RecyclerView.ViewHolder(binding.root)

}