package com.example.nyttest.network.api

import com.example.nyttest.network.model.ArticlesInfo
import com.example.nyttest.network.util.NetworkUtil
import com.example.nyttest.useCases.NetworkUseCase
import io.reactivex.Single
import okhttp3.OkHttpClient

open class BaseArticleApi (baseUrl: String,okHttpClient: OkHttpClient)
    : NewsApiService, NetworkUseCase {

    private var apiService: NewsApiService = NetworkUtil.getRetrofit(
        baseUrl,okHttpClient
    ).create(NewsApiService::class.java)

    override fun callBackArticlesData(type: String,period: String, apiKey: String): Single<ArticlesInfo> {
        return apiService.callBackArticlesData(type,period,apiKey)
    }
}