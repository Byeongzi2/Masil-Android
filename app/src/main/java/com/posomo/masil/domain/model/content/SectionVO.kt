package com.posomo.masil.domain.model.content

import com.posomo.masil.common.ui.navigation.DeepLinkDestination
import com.posomo.masil.domain.model.Store

data class SectionVO(
	val title: String,
	val viewMoreDestination: DeepLinkDestination,
	val storeList: List<Store>
) : ContentVO