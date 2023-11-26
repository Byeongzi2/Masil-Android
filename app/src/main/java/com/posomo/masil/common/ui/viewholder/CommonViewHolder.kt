package com.posomo.masil.common.ui.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.posomo.masil.domain.model.content.ContentVO

abstract class CommonViewHolder(
    binding: ViewDataBinding,
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(data: ContentVO)
}