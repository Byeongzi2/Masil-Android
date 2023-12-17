package com.posomo.masil.feature.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.posomo.masil.common.ui.adapter.CommonAdapter
import com.posomo.masil.common.ui.definition.OnViewHolderAction
import com.posomo.masil.common.ui.definition.ViewHolderActionEvent
import com.posomo.masil.common.ui.navigation.deepLinkNavigateTo
import com.posomo.masil.databinding.FragmentHomeBinding
import com.posomo.masil.domain.model.constants.ViewType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

	private var _binding: FragmentHomeBinding? = null
	private val binding get() = _binding!!

	private val viewModel by viewModels<HomeViewModel>()

	private val commonAdapter by lazy { CommonAdapter(requireActivity(), onViewHolderActionListener) }

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initUI()
		observeFlow()
	}

	private fun observeFlow() {
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.commonDataFlow.collectLatest {
					commonAdapter.submitList(it)
				}
			}
		}
	}

	private fun initUI() {
		initHomeRv()
	}

	private fun initHomeRv() {
		binding.rvHome.apply {
			setHasFixedSize(true)
			adapter = commonAdapter
		}
	}

	private val onViewHolderActionListener = object : OnViewHolderAction {
		override fun onAction(viewType: ViewType, actionEvent: ViewHolderActionEvent) =
			when (viewType) {
				ViewType.SectionViewType -> {
					provideSectionViewTypeEvent(actionEvent)
				}

				else -> {}
			}
	}

	private fun provideSectionViewTypeEvent(actionEvent: ViewHolderActionEvent) = when (actionEvent) {
		is ViewHolderActionEvent.ViewMoreEvent -> {
			val destination = actionEvent.destination
			findNavController().deepLinkNavigateTo(requireContext(), destination)
		}
		else -> {

		}
	}

//	override fun onDestroyView() {
//		super.onDestroyView()
//
//		requireActivity().setStatusBarVisible()
//	}

	companion object {
		@JvmStatic
		fun newInstance() = HomeFragment()
	}
}