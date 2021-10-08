package com.example.gotren.utils

import androidx.room.TypeConverter
import com.example.trendinglib.model.TrendingListResponseItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String): ArrayList<TrendingListResponseItem>? {
        val listType = object : TypeToken<ArrayList<TrendingListResponseItem>>() {
        }.type
        return Gson().fromJson<ArrayList<TrendingListResponseItem>>(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<TrendingListResponseItem>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}
