package com.vo.vozilla.mapactivity.presentation.vehiclemapfragment.domain

import com.vo.vozilla.repository.network.filter.models.FiltersResponse
import io.reactivex.Single

interface FiltersService {

    fun getFilters(): Single<FiltersResponse>
}