package com.example.gotren.util

import com.example.trendinglib.model.TrendingListResponseItem
import java.util.ArrayList

object MockTestUtil {


    fun mockRepositories(): List<TrendingListResponseItem> {
        val repositories = ArrayList<TrendingListResponseItem>()

        val repository1 = TrendingListResponseItem()
        repository1.author="Tarun"
        repository1.stars=1
        repository1.name="AndroidTest1"

        repositories.add(repository1)


        val repository2 = TrendingListResponseItem()
        repository2.author="Anuj"
        repository2.stars=2
        repository2.name="AndroidTest2"

        repositories.add(repository2)



        return repositories
    }
}
