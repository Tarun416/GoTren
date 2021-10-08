package com.example.trendinglib

import com.example.trendinglib.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object TrendingClient {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val api : ApiService by lazy { retrofit.create(ApiService::class.java) }


}