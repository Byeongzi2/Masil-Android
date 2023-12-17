package com.posomo.masil.feature.map.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.posomo.masil.databinding.ListItemFilterBinding
import com.posomo.masil.domain.model.FilterListItem

class FilterListAdapter : ListAdapter<FilterListItem, FilterListAdapter.FilterViewHolder>(FilterDiffUtil()) {

    override fun submitList(list: List<FilterListItem>?) {
        super.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(ListItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.onBindView(currentList[position])
    }

    inner class FilterViewHolder(private val binding: ListItemFilterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBindView(item: FilterListItem) = with(binding) {
            model = item
        }
    }

    private class FilterDiffUtil : DiffUtil.ItemCallback<FilterListItem>() {
        override fun areItemsTheSame(oldItem: FilterListItem, newItem: FilterListItem): Boolean {
            return oldItem.type == newItem.type
        }

        override fun areContentsTheSame(oldItem: FilterListItem, newItem: FilterListItem): Boolean {
            return oldItem == newItem
        }
    }
}