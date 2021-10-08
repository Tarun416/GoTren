package com.example.gotren.db

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.gotren.data.local.LocalDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
abstract class DbTest {

    lateinit var db: LocalDatabase

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().targetContext,
                LocalDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() {
        db.close()
    }
}