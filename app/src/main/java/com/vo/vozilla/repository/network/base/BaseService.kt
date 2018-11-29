package com.vo.vozilla.repository.network.base

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vo.vozilla.ktextensions.endsWithForwardSlash
import config.REST_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


abstract class BaseService<T>(val client: OkHttpClient) {

    private var restAdapter: T? = null
    private var currentBaseUrl: String? = null

    protected fun restAdapter(baseUrl: String = REST_URL, endpoint: String = ""): T {
        val refinedEndpoint = endpoint.endsWithForwardSlash()
        val refinedBaseUrl = baseUrl.endsWithForwardSlash() + refinedEndpoint

        if (restAdapter == null || refinedBaseUrl != currentBaseUrl) {
            currentBaseUrl = refinedBaseUrl
            restAdapter = buildRetrofitAPI(getGenericParameter(), client)
        }

        return restAdapter!!
    }

    protected abstract fun getGenericParameter(): Class<T>

    private fun buildRetrofitAPI(clazz: Class<T>, client: OkHttpClient): T {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(currentBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(clazz)
    }
}