package com.example.trendinglib

import com.example.trendinglib.api.ApiService
import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TrendingApiTest {

   val api  = TrendingClient.api

    @Test
    fun   `get trending list`()
    {
        runBlocking {
            val response = api.getTrendingList()
            println(response.raw().toString())
            assertNotNull(response)
        }

    }
}