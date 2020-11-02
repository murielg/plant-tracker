package com.gonzoapps.planttracker.screens.myplants

import com.gonzoapps.planttracker.models.Plant

object MockPlantProvider {

    private var data = emptyList<Plant>()

    fun dataSync() : MutableList<Plant> {
        val plant1 = Plant("Montsera")
        val plant2 = Plant("Baby cactus")
        val plant3 = Plant("Indoor Basil")
        val plant4 = Plant("Venus Flytrap")
        val plant5 = Plant("Succulent #1")
        val plant6 = Plant("Succulent #2")

        return mutableListOf(
            plant1,
            plant2,
            plant3,
            plant4,
            plant5,
            plant6
        )
    }
}