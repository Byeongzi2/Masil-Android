package com.posomo.masil.feature.map

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.posomo.masil.domain.model.FilterListItem
import com.posomo.masil.domain.model.Location
import com.posomo.masil.domain.model.StoreOnMap
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BarMapViewModel @AssistedInject constructor(
    @Assisted private val location: Location?
): ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(location: Location?) : BarMapViewModel
    }

    private val _uiState = MutableStateFlow<Event>(Event.Loading)
    val uiState: StateFlow<Event> = _uiState
    private val _userLatLng = MutableStateFlow(location)
    val userLocation: StateFlow<Location?> = _userLatLng
    val filterList: List<FilterListItem> = listOf(
        FilterListItem(type = FilterListItem.Type.KEYWORD),
        FilterListItem(type = FilterListItem.Type.LIQUOR),
        FilterListItem(type = FilterListItem.Type.STORE)
    )
    private val _storeList = MutableStateFlow<List<StoreOnMap>>(emptyList())
    val storeList: StateFlow<List<StoreOnMap>> = _storeList

    init {
        Log.d("lololo", "$location")
        loadStore()
    }

    fun setUserLatLng(lat: Double?, lng: Double?) {
        viewModelScope.launch {
            if (lat == null || lng == null) {
                _userLatLng.emit(null)
            } else {
                _userLatLng.emit(Location(lat, lng))
            }
        }
    }

    fun onLoadMapFinish() {
        viewModelScope.launch {
            _uiState.emit(Event.MapSuccess)
        }
    }

    fun loadStore() {
        viewModelScope.launch {

        }
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: Factory,
            location: Location?
        ) : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(location) as T
            }
        }
    }
}