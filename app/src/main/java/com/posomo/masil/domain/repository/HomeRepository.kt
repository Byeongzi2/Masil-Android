package com.posomo.masil.domain.repository

import com.posomo.masil.domain.model.CommonVO
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

	fun fetchHomeData(): Flow<List<CommonVO>>
}