package com.posomo.masil.feature.map

sealed interface Event {
    data object Loading: Event
    data object MapSuccess: Event
}