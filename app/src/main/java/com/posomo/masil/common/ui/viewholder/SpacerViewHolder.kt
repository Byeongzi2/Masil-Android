package com.posomo.masil.common.ui.viewholder

import com.posomo.masil.databinding.ItemSpacerBinding
import com.posomo.masil.domain.model.content.ContentVO
import com.posomo.masil.domain.model.content.SpacerVO

class SpacerViewHolder(
	private val binding: ItemSpacerBinding
) : CommonViewHolder(binding) {

	override fun bind(data: ContentVO) {
		binding.spacer = data as SpacerVO
	}
}