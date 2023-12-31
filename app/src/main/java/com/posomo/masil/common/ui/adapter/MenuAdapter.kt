package com.posomo.masil.common.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.posomo.masil.common.ui.adapter.supports.ItemDiffCallback
import com.posomo.masil.databinding.ItemMenuBinding
import com.posomo.masil.domain.model.Menu
import com.posomo.masil.domain.model.Store

class MenuAdapter(

) : ListAdapter<Menu, MenuAdapter.MenuViewHolder>(
	ItemDiffCallback<Menu>(
		onContentsTheSame = { old, new -> old == new },
		onItemsTheSame = { old, new -> old.id == new.id }
	)
) {
	class MenuViewHolder(
		private val binding: ItemMenuBinding
	) : RecyclerView.ViewHolder(binding.root){

		fun bind(item: Menu) {

		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
		val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return MenuViewHolder(binding)
	}

	override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
		holder.bind(currentList[position])
	}
}