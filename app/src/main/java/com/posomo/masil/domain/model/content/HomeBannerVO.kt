package com.posomo.masil.domain.model.content

data class HomeBannerVO(
	val title: String,
	val subTitle: String,
	val imageInfoList: List<BannerImageInfoVO>
) : ContentVO