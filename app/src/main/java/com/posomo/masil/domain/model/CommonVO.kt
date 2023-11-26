package com.posomo.masil.domain.model

import com.posomo.masil.domain.model.constants.ViewType
import com.posomo.masil.domain.model.content.ContentVO

data class CommonVO(
	val id: Long,
	val viewType: ViewType,
	val content: ContentVO
)