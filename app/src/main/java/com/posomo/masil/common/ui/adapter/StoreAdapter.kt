package com.posomo.masil.common.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.posomo.masil.common.ui.adapter.supports.ItemDiffCallback
import com.posomo.masil.common.ui.definition.StoreOrientation
import com.posomo.masil.common.ui.definition.ViewHolderActionEvent
import com.posomo.masil.common.ui.navigation.DeepLinkDestination
import com.posomo.masil.databinding.ItemStoreLandscapeBinding
import com.posomo.masil.databinding.ItemStorePortraitBinding
import com.posomo.masil.domain.model.Store

class StoreAdapter(
	private val orientation: StoreOrientation,
	private val storeClick: (ViewHolderActionEvent) -> Unit
) : ListAdapter<Store, StoreAdapter.StoreViewHolder>(
	ItemDiffCallback<Store>(
		onContentsTheSame = { old, new -> old == new },
		onItemsTheSame = { old, new -> old.id == new.id }
	)
) {
	abstract class StoreViewHolder(
		binding: ViewDataBinding,
	) : RecyclerView.ViewHolder(binding.root) {

		abstract fun bind(item: Store)
	}

	class StoreLandscapeViewHolder(
		private val binding: ItemStoreLandscapeBinding,
		private val storeClick: (ViewHolderActionEvent) -> Unit
	) : StoreViewHolder(binding) {

		override fun bind(item: Store) {
			binding.root.setOnClickListener {
				storeClick(ViewHolderActionEvent.StoreClick(DeepLinkDestination.STORE_DETAIL, item.id))
			}
			binding.store = item
		}
	}

	class StorePortraitViewHolder(
		private val binding: ItemStorePortraitBinding,
		private val storeClick: (ViewHolderActionEvent) -> Unit
	) : StoreViewHolder(binding) {

		override fun bind(item: Store) {
			binding.root.setOnClickListener {
				storeClick(ViewHolderActionEvent.StoreClick(DeepLinkDestination.STORE_DETAIL, item.id))
			}
			binding.store = item
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
		if (orientation == StoreOrientation.PORTRAIT) {
			val binding = ItemStorePortraitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
			return StorePortraitViewHolder(binding, storeClick)
		} else {
			val binding = ItemStoreLandscapeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
			return StoreLandscapeViewHolder(binding, storeClick)
		}
	}

	override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
		holder.bind(currentList[position])
	}
}