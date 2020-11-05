package com.gonzoapps.planttracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gonzoapps.planttracker.models.Plant

@Database(entities = [Plant::class], version = 1, exportSchema = false)
abstract class PlantDatabase : RoomDatabase() {

    abstract val plantDatabaseDao: PlantDatabaseDao

    companion object {
        // The value of a volatile variable will never be cached, and
        // all writes and reads will be done to and from the main memory
        // It means that changes made by one thread to INSTANCE are visible
        // to all other threads immediately, and you don't get a situation
        // where, say, two threads each update the same entity in a cache
        @Volatile
        private var INSTANCE: PlantDatabase? = null

        fun getInstance(context: Context): PlantDatabase {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            PlantDatabase::class.java,
                            "plant_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}