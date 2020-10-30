package com.gonzoapps.planttracker.models

data class Plant(
    var name: String,
    var adoptionDate: Long,
    var species: String,
    var location: String,
    var wateringSchedule: Int,
    var careInstructions: String,
)