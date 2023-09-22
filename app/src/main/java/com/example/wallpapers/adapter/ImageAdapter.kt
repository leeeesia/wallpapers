package com.example.wallpapers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpapers.databinding.CardImageBinding
import com.example.wallpapers.model.Image

interface OnInteractionListener {
    fun onOpen(image: Image) {}
    fun onFavorite(image: Image) {}
    fun onDownload(image: Image) {}
    fun onRefresh() {}
}

class ImageAdapter(
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<Image, ImageViewHolder>(ImageDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = CardImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        holder.bind(image)
    }
}

class ImageViewHolder(
    private val binding: CardImageBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(image: Image) {
        binding.apply {
            val url = image.urls?.regular
            Glide.with(im)
                .load(url)
                .centerCrop()
                .timeout(10_000)
                .into(im)
            im.setOnClickListener {
                onInteractionListener.onOpen(image)
            }
        }

    }
}

class ImageDiffCallback : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }
}