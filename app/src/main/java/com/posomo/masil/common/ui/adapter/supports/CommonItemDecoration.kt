package com.posomo.masil.common.ui.adapter.supports

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.posomo.masil.common.util.dp

/**
 * Android attrs.xml 에 명시
 */
private const val HORIZONTAL = 0
private const val VERTICAL = 1

class CommonItemDecoration : RecyclerView.ItemDecoration() {

	override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
		super.getItemOffsets(outRect, view, parent, state)

		when(parent.layoutManager) {
			is LinearLayoutManager -> {
				initLinearLayoutManagerItemDecoration(
					outRect = outRect,
					view = view,
					parent = parent
				)
			}
			else -> {}
		}
	}

	private fun initLinearLayoutManagerItemDecoration(outRect: Rect, view: View, parent: RecyclerView) {
		val orientation = (parent.layoutManager as LinearLayoutManager).orientation
		val position = parent.getChildAdapterPosition(view)
		if (orientation == HORIZONTAL) {
			val childCount = parent.childCount

			val padding = if (position == childCount - 1) 24.dp else 16.dp
			outRect.right = padding

			if (position == 0) {
				outRect.left = 24.dp
			}
		}
	}
}