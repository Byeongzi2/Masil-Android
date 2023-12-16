package com.posomo.masil.common.ui.viewholder

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.posomo.masil.common.ui.adapter.BannerImageSlideAdapter
import com.posomo.masil.common.util.getStatusBarHeight
import com.posomo.masil.common.util.setStatusBarTransparent
import com.posomo.masil.databinding.ItemHomeBannerBinding
import com.posomo.masil.domain.model.content.BannerImageInfoVO
import com.posomo.masil.domain.model.content.ContentVO
import com.posomo.masil.domain.model.content.HomeBannerVO
import kotlin.math.abs

class HomeBannerViewHolder(
	private val binding: ItemHomeBannerBinding,
	private val activity: Activity
) : CommonViewHolder(binding) {

	private var isBannerBackgroundHeightResized = false

	override fun bind(data: ContentVO) {
		val bannerInfo = data as HomeBannerVO

//		adjustStatusBarToTransparent()
		initHomeBanner(bannerInfo)
	}

	private fun initHomeBanner(bannerInfo: HomeBannerVO) {
		initHomeBannerSetting(bannerInfo.imageInfoList)
		initHomeBannerHeight()
	}

	private fun initHomeBannerSetting(
		bannerImageInfoList: List<BannerImageInfoVO>,
		bannerOffsetScreenPageLimit: Int = 3,
	) {
		binding.vpHomeBannerImages.apply {
			adapter = BannerImageSlideAdapter(bannerImageInfoList)
			clipToPadding = false
			clipChildren = false
			offscreenPageLimit = bannerOffsetScreenPageLimit
			getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
			setPageTransformer(getCompositePageTransformer())
			registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
				override fun onPageSelected(position: Int) {
					super.onPageSelected(position)

					initBannerBackground(position)
				}

				private fun initBannerBackground(position: Int) {
					Glide.with(binding.homeInnerContainer)
						.load(bannerImageInfoList[position].blurImageUrl)
						.into(binding.imgHomeTopAreaBackground)
				}
			})
		}
	}

	private fun initHomeBannerHeight() {
		binding.vpHomeBannerImages.viewTreeObserver.addOnGlobalLayoutListener {
			if (!isBannerBackgroundHeightResized) {
				val bgLp = binding.imgHomeTopAreaBackground.layoutParams
				bgLp.height = binding.vpHomeBannerImages.bottom - 100
				binding.imgHomeTopAreaBackground.layoutParams = bgLp
				isBannerBackgroundHeightResized = true
			}
		}
	}

	private fun getCompositePageTransformer() =
		CompositePageTransformer().apply {
			addTransformer(MarginPageTransformer(40))
			addTransformer { page, position ->
				val r = 1 - abs(position)
				page.scaleY = 0.85f + r * 0.15f
			}
		}

	private fun adjustStatusBarToTransparent() {
		activity.setStatusBarTransparent()
		binding.homeInnerContainer.setPadding(
			0,
			activity.getStatusBarHeight(),
			0,
			0
		)
	}
}