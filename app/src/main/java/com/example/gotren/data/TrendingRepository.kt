package com.example.gotren.data

import android.util.Log
import com.example.gotren.TrendingApplication
import com.example.gotren.data.local.entitiy.TrendingData
import com.example.trendinglib.TrendingClient
import com.example.trendinglib.model.TrendingListResponseItem
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TrendingRepository {

    val api = TrendingClient.api
    val dao = TrendingApplication().getDatabase().getTrendingDao()

    fun getTrendingListFromDb(): Single<TrendingData> {
        return dao.getTrendingList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTrendingList(): Single<List<TrendingListResponseItem>> {
        return api.getTrendingListRx()
            .subscribeOn(Schedulers.io())
            .map { trendingListResponse ->
                dao.deleteTrendingList()
                try {
                    dao
                        .saveTrendingList(
                            TrendingData(
                                0,
                                trendingListResponse.body()!!
                            )
                        )
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    Log.d("Addedid exception", ex.message+"")
                }

                trendingListResponse
            }
            .map{
                it.body()!!.toList()
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}