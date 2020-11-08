/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gonzoapps.planttracker.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gonzoapps.planttracker.models.Plant

@Dao
interface PlantDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plant: Plant)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(plant: Plant)

    @Query("SELECT * FROM plants_table WHERE plantId = :id")
    suspend fun get(id: String) : Plant?

    @Query("DELETE FROM plants_table")
    suspend fun clearTable()

    @Query("SELECT * FROM plants_table ORDER BY plantId DESC")
    fun getAllPlants(): LiveData<List<Plant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkInsert(plants: List<Plant>)

    @Transaction
    open suspend fun updateData(users: List<Plant>) {
        clearTable()
        bulkInsert(users)
    }
}
