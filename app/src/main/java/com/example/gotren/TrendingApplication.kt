package com.example.gotren

import android.app.Application
import androidx.room.Room
import com.example.gotren.data.local.LocalDatabase
import com.example.gotren.data.local.Preferences
import com.example.gotren.data.local.dao.TrendingDao

class TrendingApplication : Application() {

    companion object {

        lateinit var appDatabase: LocalDatabase
        private const val DB_NAME = "trendinglist.db"

    }

    override fun onCreate() {
        super.onCreate()
        appDatabase = Room.databaseBuilder(this, LocalDatabase::class.java, DB_NAME).fallbackToDestructiveMigration().build()
        Preferences.init(this)
    }


    fun getDatabase(): LocalDatabase{
        return appDatabase
    }



}