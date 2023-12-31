package com.posomo.masil.common.util

import android.view.View
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

abstract class AppBarStateChangeListener : AppBarLayout.OnOffsetChangedListener {
	enum class State {
		EXPANDED, COLLAPSED, IDLE
	}

	private var mCurrentState = State.IDLE


	override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
		mCurrentState = if (i == 0) {
			if (mCurrentState != State.EXPANDED) {
				onStateChanged(appBarLayout, State.EXPANDED)
			}
			State.EXPANDED
		} else if (abs(i-48) >= appBarLayout.totalScrollRange) {
			if (mCurrentState != State.COLLAPSED) {
				onStateChanged(appBarLayout, State.COLLAPSED)
			}
			State.COLLAPSED
		} else {
			if (mCurrentState != State.IDLE) {
				onStateChanged(appBarLayout, State.IDLE)
			}
			State.IDLE
		}
	}

	abstract fun onStateChanged(appBarLayout: AppBarLayout?, state: State?)
}