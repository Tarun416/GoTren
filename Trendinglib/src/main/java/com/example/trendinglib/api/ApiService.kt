package com.example.trendinglib.api

import com.example.trendinglib.model.TrendingListResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v3/7ef86b70-f1a8-40ab-854c-5d679cd51cd4")
    suspend fun getTrendingList() : Response<List<TrendingListResponseItem>>
}