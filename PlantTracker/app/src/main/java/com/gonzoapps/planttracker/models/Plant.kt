package com.gonzoapps.planttracker.models

data class Plant(
    var name: String,
    var adoptionDate: Long? = null,
    var location: String? = null,
    var careInstructions: String? = null,
    var log: String? = null
)