package com.posomo.masil.common.ui.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import com.posomo.masil.common.ui.adapter.StoreAdapter
import com.posomo.masil.common.ui.adapter.supports.CommonItemDecoration
import com.posomo.masil.common.ui.definition.StoreOrientation
import com.posomo.masil.common.ui.definition.ViewHolderActionEvent
import com.posomo.masil.databinding.ItemSectionBinding
import com.posomo.masil.domain.model.content.ContentVO
import com.posomo.masil.domain.model.content.SectionVO

/**
 * Android attrs.xml 에 명시
 */
private const val HORIZONTAL = 0
private const val VERTICAL = 1

class SectionViewHolder(
	private val binding: ItemSectionBinding,
	private val onClick: (ViewHolderActionEvent) -> Unit = {}
) : CommonViewHolder(binding) {
	override fun bind(data: ContentVO) {
		val section = data as SectionVO

		val orientation = (binding.rvItemSectionStore.layoutManager as LinearLayoutManager).orientation
		val storeOrientation = if (orientation == HORIZONTAL) {
			StoreOrientation.PORTRAIT
		} else {
			StoreOrientation.LANDSCAPE
		}
		val storeAdapter = StoreAdapter(storeOrientation)

		binding.section = section
		binding.itemSectionViewMore.setOnClickListener {
			onClick(ViewHolderActionEvent.ViewMoreEvent(section.viewMoreDestination))
		}
		binding.rvItemSectionStore.apply {
			adapter = storeAdapter
			addItemDecoration(CommonItemDecoration())
		}
		storeAdapter.submitList(section.storeList)
	}
}