package com.gonzoapps.planttracker.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "plants_table")
data class Plant(
    @ColumnInfo(name = "plant_name")
    var name: String = "",

    @ColumnInfo(name = "plant_location")
    var location: String = "",

    @ColumnInfo(name = "plant_care")
    var careInstructions: String = "",

    @ColumnInfo(name = "plant_log")
    var log: String = "",

    @PrimaryKey
    var plantId: String = UUID.randomUUID().toString()
){
    val isEmpty
        get() = name.isEmpty() || location.isEmpty()
}