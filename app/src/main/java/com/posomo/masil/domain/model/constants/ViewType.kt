package com.posomo.masil.domain.model.constants

import com.posomo.masil.domain.model.content.HomeBannerVO
import com.posomo.masil.domain.model.content.SectionVO
import com.posomo.masil.domain.model.content.SpacerVO
import com.posomo.masil.domain.model.content.UnknownVO
import java.lang.reflect.Type

enum class ViewType(
	private val viewTypeClass: Type
) {
	HomeBannerViewType(HomeBannerVO::class.java),
	SectionViewType(SectionVO::class.java),
	SpacerViewType(SpacerVO::class.java),
	UnknownViewType(UnknownVO::class.java);

	companion object {
		fun findClassByItsName(viewTypeString: String?): ViewType {
			values().find { it.name == viewTypeString }?.let {
				return it
			} ?: return UnknownViewType
		}

		fun findViewTypeClassByItsName(viewTypeString: String?): Type {
			return findClassByItsName(viewTypeString).viewTypeClass
		}

		fun getViewTypeByOrdinal(ordinalNum: Int): ViewType {
			return values()[ordinalNum]
		}
	}
}