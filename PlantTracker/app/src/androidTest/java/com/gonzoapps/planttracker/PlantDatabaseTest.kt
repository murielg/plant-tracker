package com.gonzoapps.planttracker

import android.content.Context
import androidx.lifecycle.*
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gonzoapps.planttracker.db.PlantDatabase
import com.gonzoapps.planttracker.db.PlantDatabaseDao
import com.gonzoapps.planttracker.models.Plant
import com.gonzoapps.planttracker.screens.myplants.MockPlantProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.io.IOException


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PlantDatabaseTest {
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
        assertEquals(plantDao.getAllPlants().value?.size, null)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGet() = runBlocking {
        val plantId = plantDao.insert(Plant("Maggie the Montsera🌱", "Living Room", "", ""))
        val plant = plantDao.get(plantId)
        assertNotNull(plant)
        assertEquals(plant?.plantId, plantId)
    }

    @Test
    @Throws(Exception::class)
    fun bulkInsert(){
        val plants = MockPlantProvider.dataSync()
        val plantsSize = MockPlantProvider.dataSync().size

        runBlocking {
            plantDao.bulkInsert(plants)
        }

        GlobalScope.launch(Dispatchers.Main) {
            val dbPlants = plantDao.getAllPlants()
            dbPlants.observe(mockLifecycleOwner()!!, Observer {
                assertNotNull(it)
                assertEquals(it.size,plantsSize)
            })
        }
    }

    private fun mockLifecycleOwner(): LifecycleOwner? {
        val owner: LifecycleOwner = mock(LifecycleOwner::class.java)
        val lifecycle = LifecycleRegistry(owner)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        `when`(owner.lifecycle).thenReturn(lifecycle)
        return owner
    }
}