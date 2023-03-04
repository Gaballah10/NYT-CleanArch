package com.example.nyttest.useCases.impl

import com.example.nyttest.network.api.NewsApiService
import com.example.nyttest.network.model.ArticlesInfo
import com.example.nyttest.useCases.NetworkUseCase
import io.reactivex.Single

class NetworkUseCaseImpl(private val newsApi: NewsApiService) : NetworkUseCase {

    override fun callBackArticlesData(type: String,period: String, apiKey: String): Single<ArticlesInfo> {
        return newsApi.callBackArticlesData(type,period,apiKey)
    }
}