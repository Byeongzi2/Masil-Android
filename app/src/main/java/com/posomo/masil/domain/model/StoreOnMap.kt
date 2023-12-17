package com.posomo.masil.domain.model

data class StoreOnMap(
    val id: Long,
    val name: String,
    val location: String,
    val category: String,
    val isOpen: Boolean?,
    val isBookMarked: Boolean,
    val images: List<String>
)
