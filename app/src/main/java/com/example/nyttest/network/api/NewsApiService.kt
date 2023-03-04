package com.example.nyttest.network.api

import com.example.nyttest.network.model.ArticlesInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApiService {

    @GET("{type}/{Period}.json?")
    fun callBackArticlesData(
        @Path("type") type: String,
        @Path("Period") period: String,
        @Query("api-key") apiKey: String
    ): Single<ArticlesInfo>

}