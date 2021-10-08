package com.example.trendinglib.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
    data class TrendingListResponseItem(
        @Json(name = "author")
        val author: String? = null,
        @Json(name = "avatar")
        val avatar: String? = null,
        @Json(name = "currentPeriodStars")
        val currentPeriodStars: Int? = null,
        @Json(name = "description")
        val description: String? = null,
        @Json(name = "forks")
        val forks: Int? = null,
        @Json(name = "language")
        val language: String? = null,
        @Json(name = "languageColor")
        val languageColor: String? = null,
        @Json(name = "name")
        val name: String? = null,
        @Json(name = "stars")
        val stars: Int? = null,
        @Json(name = "url")
        val url: String? = null
    )