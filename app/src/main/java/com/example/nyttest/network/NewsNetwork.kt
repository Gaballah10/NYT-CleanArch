package com.example.nyttest.network

import com.example.nyttest.BuildConfig
import com.example.nyttest.network.api.BaseArticleApi
import com.example.nyttest.network.util.NetworkUtil
import com.example.nyttest.useCases.NetworkUseCase
import okhttp3.Interceptor

object NewsNetwork {
    fun getArticleApi(
        baseUrl: String = BuildConfig.BASE_URL,
        interceptors: List<Interceptor> = listOf(),
        networkInterceptors: List<Interceptor> = listOf(),
    ): NetworkUseCase {

        val interceptorList = mutableListOf<Interceptor>()
        interceptorList.addAll(interceptors)

        return BaseArticleApi(
            baseUrl = baseUrl,
            okHttpClient = NetworkUtil.getOkHttpClient(
                interceptors = interceptorList,
                networkInterceptors = networkInterceptors
            )
        )

    }
}
