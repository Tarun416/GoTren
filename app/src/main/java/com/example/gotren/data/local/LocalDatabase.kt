package com.example.gotren.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gotren.data.local.dao.TrendingDao
import com.example.gotren.data.local.entitiy.TrendingData
import com.example.gotren.utils.Converters


@Database(
    version = 1,
    entities = [(TrendingData::class)]
)

@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getTrendingDao(): TrendingDao
}
