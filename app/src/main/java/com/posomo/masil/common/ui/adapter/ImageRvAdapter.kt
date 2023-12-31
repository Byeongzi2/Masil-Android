package com.posomo.masil.common.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.posomo.masil.common.ui.adapter.supports.ItemDiffCallback
import com.posomo.masil.databinding.ItemImageBinding
import com.posomo.masil.domain.model.Image

class ImageRvAdapter (

) : ListAdapter<Image, ImageRvAdapter.ImageViewHolder>(
	ItemDiffCallback<Image>(
		onContentsTheSame = { old, new -> old == new },
		onItemsTheSame = { old, new -> old.id == new.id }
	)
) {
	class ImageViewHolder(
		private val binding: ItemImageBinding
	) : RecyclerView.ViewHolder(binding.root){

		fun bind(item: Image) {
			Glide.with(binding.ivImage.context)
				.load(item.url)
				.into(binding.ivImage)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
		val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return ImageViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
		holder.bind(currentList[position])
	}
}