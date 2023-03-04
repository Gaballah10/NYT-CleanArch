package com.example.nyttest.useCases

import com.example.nyttest.network.model.ArticlesInfo
import io.reactivex.Single

interface NetworkUseCase {

    fun callBackArticlesData(type: String,period: String,apiKey: String): Single<ArticlesInfo>

}