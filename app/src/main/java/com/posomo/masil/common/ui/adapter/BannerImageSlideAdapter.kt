package com.posomo.masil.common.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.posomo.masil.databinding.SlideItemContainerBinding
import com.posomo.masil.domain.model.content.BannerImageInfoVO

class BannerImageSlideAdapter(
	private val items: List<BannerImageInfoVO>
) : RecyclerView.Adapter<BannerImageSlideAdapter.SliderViewHolder>() {

	class SliderViewHolder(private val binding: SlideItemContainerBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(item: String) {
			Glide.with(itemView.context)
				.load(item)
				.into(binding.slideItemContainerImage)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
		val binding = SlideItemContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return SliderViewHolder(binding)
	}

	override fun getItemCount(): Int = items.size

	override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
		holder.bind(items[position].imageUrl)
	}
}