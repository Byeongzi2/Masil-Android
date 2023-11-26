package com.posomo.masil.feature.temp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.posomo.masil.databinding.FragmentTempBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TempFragment : Fragment() {

	private var _binding: FragmentTempBinding? = null
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentTempBinding.inflate(inflater, container, false)
		return binding.root
	}

	companion object {
		@JvmStatic
		fun newInstance() = TempFragment()
	}
}