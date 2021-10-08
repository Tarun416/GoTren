package com.example.gotren.data.local.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trendinglib.model.TrendingListResponseItem

@Entity
data class TrendingData
(
        @PrimaryKey(autoGenerate = true)
        var id: Int,

        var list: ArrayList<TrendingListResponseItem>
)