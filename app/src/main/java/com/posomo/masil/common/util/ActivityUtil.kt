package com.posomo.masil.common.util

import android.app.Activity
import android.os.Build
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.core.view.WindowCompat

fun Activity.setStatusBarTransparent() {
	window.apply {
		setFlags(
			WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
			WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
		)
	}
	if (Build.VERSION.SDK_INT >= 30) {    // API 30 에 적용
		WindowCompat.setDecorFitsSystemWindows(window, false)
	}
}

fun Activity.setStatusBarVisible() {
	window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

	if (Build.VERSION.SDK_INT >= 30) {
		WindowCompat.setDecorFitsSystemWindows(window, true)
	}
}


fun Activity.getStatusBarHeight(): Int {
	val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
	return if (resourceId > 0) {
		resources.getDimensionPixelSize(resourceId)
	} else {
		0
	}
}

fun Activity.setStatusBarTextColor(isLightStatusBar: Boolean) {
	WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = isLightStatusBar
}