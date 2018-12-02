package com.vo.vozilla.repository.network.filter

import com.vo.vozilla.repository.network.filter.models.FiltersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface FilterRetrofitApi {

    @Headers("Content-Type: application/json")
    @GET("map/filters/")
    fun getFilters(): Single<FiltersResponse>
}