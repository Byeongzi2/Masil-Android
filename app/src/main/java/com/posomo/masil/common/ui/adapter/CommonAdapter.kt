package com.posomo.masil.common.ui.adapter

import android.app.Activity
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.posomo.masil.common.ui.adapter.supports.ItemDiffCallback
import com.posomo.masil.common.ui.definition.OnViewHolderAction
import com.posomo.masil.common.ui.viewholder.CommonVHFactory
import com.posomo.masil.common.ui.viewholder.CommonViewHolder
import com.posomo.masil.domain.model.CommonVO
import com.posomo.masil.domain.model.constants.ViewType

class CommonAdapter(
	private val activity: Activity,
	private val onViewHolderActionListener: OnViewHolderAction
) : ListAdapter<CommonVO, CommonViewHolder>(
	ItemDiffCallback<CommonVO>(
		onContentsTheSame = { old, new -> old == new },
		onItemsTheSame = { old, new -> old.id == new.id }
	)
) {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
		return CommonVHFactory.createViewHolder(
			parent = parent,
			viewType = ViewType.getViewTypeByOrdinal(viewType),
			activity = activity,
			onClick = { viewHolderViewType, action ->
				onViewHolderActionListener.onAction(viewHolderViewType, action)
			}
		)
	}

	override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
		holder.bind(getItem(position).content)
	}

	override fun getItemViewType(position: Int): Int {
		return getItem(position).viewType.ordinal
	}
}