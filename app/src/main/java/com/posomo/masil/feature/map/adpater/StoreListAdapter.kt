package com.posomo.masil.feature.map.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.posomo.masil.databinding.ListItemMapStoreBinding
import com.posomo.masil.domain.model.StoreOnMap

class StoreListAdapter: ListAdapter<StoreOnMap, StoreListAdapter.StoreViewHolder>(StoreDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder(ListItemMapStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.onBindView(currentList[position])
    }

    inner class StoreViewHolder(private val binding: ListItemMapStoreBinding): RecyclerView.ViewHolder(binding.root) {

        fun onBindView(item: StoreOnMap) = with(binding) {
            model = item
        }
    }

    private class StoreDiffUtil: DiffUtil.ItemCallback<StoreOnMap>() {
        override fun areItemsTheSame(oldItem: StoreOnMap, newItem: StoreOnMap): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: StoreOnMap, newItem: StoreOnMap): Boolean {
            return oldItem == newItem
        }
    }
}