package com.gonzoapps.planttracker.models

data class Plant(
    var name: String,
    var adoptionDate: Long,
    var location: String,
    var careInstructions: String,
    var log: String
)