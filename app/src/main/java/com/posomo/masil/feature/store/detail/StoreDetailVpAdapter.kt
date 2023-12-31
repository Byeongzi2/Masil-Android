package com.posomo.masil.feature.store.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class StoreDetailVpAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
	override fun getItemCount(): Int = 2

	override fun createFragment(position: Int): Fragment {
		TODO("Not yet implemented")
	}

}