package com.posomo.masil.common.ui.viewholder

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.posomo.masil.R
import com.posomo.masil.common.ui.definition.ViewHolderActionEvent
import com.posomo.masil.domain.model.constants.ViewType

object CommonVHFactory {
	fun createViewHolder(
		parent: ViewGroup,
		viewType: ViewType,
		activity: Activity,
		onClick: (ViewType, ViewHolderActionEvent) -> Unit = { _, _ -> }
	): CommonViewHolder {
		return when (viewType) {
			ViewType.HomeBannerViewType -> HomeBannerViewHolder(
				binding = getViewDataBinding(parent, R.layout.item_home_banner),
				activity = activity
			)
			ViewType.SectionViewType -> SectionViewHolder(
				binding = getViewDataBinding(parent, R.layout.item_section),
				onClick = { action ->
					onClick(viewType, action)
				}
			)
			ViewType.SpacerViewType -> SpacerViewHolder(getViewDataBinding(parent, R.layout.item_spacer))
			ViewType.UnknownViewType -> UnknownViewHolder(getViewDataBinding(parent, R.layout.item_unknown))
		}
	}

	private fun <T : ViewDataBinding> getViewDataBinding(parent: ViewGroup, layoutRes: Int): T {
		return DataBindingUtil.inflate(
			LayoutInflater.from(parent.context),
			layoutRes,
			parent,
			false
		)
	}
}