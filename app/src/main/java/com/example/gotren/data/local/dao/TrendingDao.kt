package com.example.gotren.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gotren.data.local.entitiy.TrendingData
import io.reactivex.Single

@Dao
interface TrendingDao {
    @Query("SELECT * FROM TrendingData")
    fun getTrendingList(): Single<TrendingData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTrendingList(trendingListResponse: TrendingData): Long

    @Query("DELETE FROM TrendingData")
    fun deleteTrendingList()

    @Query("SELECT * FROM TrendingData")
    fun getTrendingListForTest(): TrendingData
}