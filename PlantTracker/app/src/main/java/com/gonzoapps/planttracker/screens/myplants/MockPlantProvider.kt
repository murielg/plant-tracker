package com.gonzoapps.planttracker.screens.myplants

import com.gonzoapps.planttracker.models.Plant

object MockPlantProvider {

    private var data = emptyList<Plant>()

    fun dataSync() : List<Plant> {
        val plant1 = Plant("montsera")
        val plant2 = Plant("baby cactus")
        val plant3 = Plant("indoor basil")

        return listOf(
            plant1,plant2,plant3,plant1,plant2,plant3
        )
    }
}