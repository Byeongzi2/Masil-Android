package com.posomo.masil.common.ui.navigation

import android.content.Context
import com.posomo.masil.R

enum class DeepLinkDestination(val addressRes: Int) {
	HOME(R.string.home_deeplink_url),
	MAP(R.string.map_deeplink_url),
	BOOKMARK(R.string.bookmark_deeplink_url),
	TEMP1(R.string.temp1_deeplink_url),
	TEMP2(R.string.temp2_deeplink_url),
	NONE(-1);

	companion object {
		fun findDeepLinkDestination(context: Context, deepLink: String) =
			values().firstOrNull { context.getString(it.addressRes) == deepLink } ?: NONE
	}
}