package com.posomo.masil.domain.model

data class FilterListItem(
    val type: Type
) {
    enum class Type {
        KEYWORD, LIQUOR, STORE
    }
}
