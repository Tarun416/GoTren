package com.example.gotren.data

import com.example.trendinglib.TrendingClient
import com.example.trendinglib.model.TrendingListResponseItem

class TrendingRepository {

    val api = TrendingClient.api

    suspend fun getTrendingList() : List<TrendingListResponseItem>
    {
        val response = api.getTrendingList()
        return response.body()!!
    }
}