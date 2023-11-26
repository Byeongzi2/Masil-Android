package com.posomo.masil.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.posomo.masil.data.repository.HomeRepositoryImpl
import com.posomo.masil.domain.model.CommonVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {

	private val _commonDataFlow = MutableStateFlow<List<CommonVO>>(emptyList())
	val commonDataFlow get() = _commonDataFlow.asStateFlow()

	init {
		getHomeRvDummyData()
	}

	fun getHomeRvDummyData(){
		viewModelScope.launch {
			HomeRepositoryImpl().fetchHomeData().collectLatest {
				_commonDataFlow.value = it
			}
		}

	}
}