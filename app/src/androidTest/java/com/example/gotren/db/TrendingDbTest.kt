package com.example.gotren.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gotren.data.local.entitiy.TrendingData

import com.example.gotren.util.MockTestUtil
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TrendingDbTest : DbTest() {

    @Test
    fun insertAndReadTrendingTest() {
        val repositories = MockTestUtil.mockRepositories()
        db.getTrendingDao().saveTrendingList(TrendingData(0, ArrayList(repositories)))

        val storedTrending1 = db.getTrendingDao().getTrendingListForTest()
        Assert.assertEquals(2, storedTrending1.list.size)
        Assert.assertEquals("Tarun", storedTrending1.list[0].author)

    }


    @Test
    fun deleteTrendingTest() {
        val repositories = MockTestUtil.mockRepositories()
        db.getTrendingDao().saveTrendingList(TrendingData(0, ArrayList(repositories)))
        db.getTrendingDao().deleteTrendingList()

        val storedTrending1 = db.getTrendingDao().getTrendingListForTest()
        Assert.assertEquals(null, storedTrending1)
    }
}
