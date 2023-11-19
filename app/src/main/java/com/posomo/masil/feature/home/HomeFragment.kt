package com.posomo.masil.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.posomo.masil.common.util.getStatusBarHeight
import com.posomo.masil.common.util.setStatusBarTransparent
import com.posomo.masil.databinding.FragmentHomeBinding
import com.posomo.masil.model.HomeBannerImageInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : Fragment() {

	private var _binding: FragmentHomeBinding? = null
	private val binding get() = _binding!!

	private val viewModel by viewModels<HomeViewModel>()

	private var isBannerBackgroundHeightResized = false

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		adjustStatusBarToTransparent()
		initHomeBanner()
	}

	private fun initHomeBanner() {
		initHomeBannerSetting(viewModel.getDummyData().imageInfoList)
		initHomeBannerHeight()
	}

	private fun initHomeBannerSetting(
		bannerImageInfoList: List<HomeBannerImageInfo>,
		bannerOffsetScreenPageLimit: Int = 3,
	) {
		binding.vpHomeBannerImages.apply {
			adapter = HomeBannerImageSlideAdapter(bannerImageInfoList)
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
					Glide.with(requireActivity())
						.load(viewModel.getDummyData().imageInfoList[position].blurImageUrl)
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
		requireActivity().setStatusBarTransparent()
		binding.homeInnerContainer.setPadding(
			0,
			requireActivity().getStatusBarHeight(),
			0,
			0
		)
	}


	companion object {
		@JvmStatic
		fun newInstance() = HomeFragment()
	}
}