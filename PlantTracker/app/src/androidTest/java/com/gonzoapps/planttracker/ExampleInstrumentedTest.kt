package com.gonzoapps.planttracker

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gonzoapps.planttracker.db.PlantDatabase
import com.gonzoapps.planttracker.db.PlantDatabaseDao
import com.gonzoapps.planttracker.models.Plant
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var plantDao: PlantDatabaseDao
    private lateinit var db: PlantDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PlantDatabase::class.java)
            .build()
        plantDao = db.plantDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndClearTable() = runBlocking {
        val plant = Plant("Maggie the Montsera🌱", "Living Room", "", "")
        plantDao.insert(plant)
        plantDao.clearTable()
        assertEquals(plantDao.getAllPlants().value?.size,null)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGet() = runBlocking {
        val plantId = plantDao.insert(Plant("Maggie the Montsera🌱", "Living Room", "", ""))
        val plant = plantDao.get(plantId)
        assertEquals(plant?.plantId,plantId)
    }
}