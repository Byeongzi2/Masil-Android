package com.posomo.masil.feature.store.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.posomo.masil.common.util.AppBarStateChangeListener
import com.posomo.masil.databinding.FragmentStoreDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreDetailFragment : Fragment() {

	private var _binding: FragmentStoreDetailBinding? = null
	val binding get() = _binding!!

	private var appBarState: AppBarStateChangeListener.State = AppBarStateChangeListener.State.EXPANDED

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentStoreDetailBinding.inflate(inflater, container, false)
		binding.lifecycleOwner = viewLifecycleOwner
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.appBarState = appBarState

		binding.appBar.addOnOffsetChangedListener(object: AppBarStateChangeListener() {
			override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
				Log.e("Test@@@", "appBarState: $appBarState")
				appBarState = state ?: State.IDLE
			}
		})
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}