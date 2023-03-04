package com.example.nyttest.network.util


import com.squareup.moshi.Moshi
import okhttp3.Authenticator
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


object NetworkUtil {

    fun getOkHttpClient(interceptors: List<Interceptor> = listOf(),
                        networkInterceptors: List<Interceptor> = listOf()): OkHttpClient {
        val builder = OkHttpClient().newBuilder()

        networkInterceptors.map {
            builder.addNetworkInterceptor(it)
        }
        interceptors.map {
            builder.addInterceptor(it)
        }


        return builder.build()
    }

    fun getRetrofit(baseUrl: String,okHttpClient: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            // .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}