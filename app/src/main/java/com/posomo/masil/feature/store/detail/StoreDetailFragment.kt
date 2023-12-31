package com.posomo.masil.feature.store.detail

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.posomo.masil.R
import com.posomo.masil.common.ui.adapter.ImageRvAdapter
import com.posomo.masil.common.ui.adapter.MenuAdapter
import com.posomo.masil.common.ui.adapter.StoreAdapter
import com.posomo.masil.common.ui.adapter.supports.CommonItemDecoration
import com.posomo.masil.common.ui.definition.StoreOrientation
import com.posomo.masil.common.util.AppBarStateChangeListener
import com.posomo.masil.databinding.FragmentStoreDetailBinding
import com.posomo.masil.domain.model.Image
import com.posomo.masil.domain.model.Menu
import com.posomo.masil.domain.model.Store
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreDetailFragment : Fragment() {

	private var _binding: FragmentStoreDetailBinding? = null
	val binding get() = _binding!!

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentStoreDetailBinding.inflate(inflater, container, false)
		binding.lifecycleOwner = viewLifecycleOwner
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.appbar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
			override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
				with(binding.tvStoreDetailNameInTb) {
					visibility = if (state == State.COLLAPSED) View.VISIBLE else View.GONE
					text = "hello"
				}
				val whiteColor = getColor(requireContext().resources, R.color.white, null)
				val blackColor = getColor(requireContext().resources, R.color.black, null)
				binding.icStoreDetailArrowBack.setColorFilter(if (state == State.COLLAPSED) blackColor else whiteColor)
				binding.icStoreDetailHome.setColorFilter(if (state == State.COLLAPSED) blackColor else whiteColor)
				binding.tbStoreDetail.setBackgroundColor(if (state == State.COLLAPSED) Color.WHITE else Color.TRANSPARENT)
			}
		})

		val imageList = listOf(
			Image(
				id = 1L,
				url = "https://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg"
			),
			Image(
				id = 2L,
				url = "https://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg"
			),
			Image(
				id = 3L,
				url = "https://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg"
			),
		)
		val imageRvAdapter = ImageRvAdapter()
		binding.rvStoreDetailRepresentativeMenu.apply {
			adapter = imageRvAdapter
			addItemDecoration(CommonItemDecoration())
		}
		imageRvAdapter.submitList(imageList)

		val menuList = listOf(
			Menu(
				id = 1L,
				name = "메뉴",
				description = "메뉴에 대한 설명",
				price = 1000
			)
		)

		val alcoholAdapter = MenuAdapter()
		binding.rvStoreDetailAlcohol.apply {
			adapter = alcoholAdapter
		}

		val sideDishAdapter = MenuAdapter()
		binding.rvStoreDetailSideDish.apply {
			adapter = sideDishAdapter
		}

		alcoholAdapter.submitList(menuList)
		sideDishAdapter.submitList(menuList)

		val similarStoreList = listOf(
			Store(
				id = 100L,
				imageUrl = "https://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg",
				isBookmarked = false,
				name = "{주점명1}",
				location = "{지역}",
				category = "{업태}"
			),
			Store(
				id = 101L,
				imageUrl = "https://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg",
				isBookmarked = false,
				name = "{주점명2}",
				location = "{지역}",
				category = "{업태}"
			),
			Store(
				id = 102L,
				imageUrl = "https://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg",
				isBookmarked = false,
				name = "{주점명3}",
				location = "{지역}",
				category = "{업태}"
			)
		)
		val similarStoreAdapter = StoreAdapter(orientation = StoreOrientation.PORTRAIT)
		binding.rvStoreDetailSimilarArea.apply {
			adapter = similarStoreAdapter
			addItemDecoration(CommonItemDecoration())
		}
		similarStoreAdapter.submitList(similarStoreList)
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}