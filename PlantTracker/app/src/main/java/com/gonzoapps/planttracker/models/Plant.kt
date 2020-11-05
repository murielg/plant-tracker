package com.gonzoapps.planttracker.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants_table")
data class Plant(
    @ColumnInfo(name = "plant_name")
    var name: String,

    @ColumnInfo(name = "plant_location")
    var location: String? = null,

    @ColumnInfo(name = "plant_care")
    var careInstructions: String? = null,

    @ColumnInfo(name = "plant_log")
    var log: String? = null,

    @ColumnInfo(name = "plant_icon")
    var icon: Int = -1
) {
    @PrimaryKey(autoGenerate = true)
    var plantId: Long = 0L
}