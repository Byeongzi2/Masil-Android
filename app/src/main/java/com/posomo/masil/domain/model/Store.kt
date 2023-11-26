package com.posomo.masil.domain.model

data class Store(
	val id: Long,
	val imageUrl: String,
	val isBookmarked: Boolean,
	val name: String,
	val location: String,
	val category: String
)