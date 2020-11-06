package com.gonzoapps.planttracker.screens.myplants

import com.gonzoapps.planttracker.models.Plant

object MockPlantProvider {

    private var data = emptyList<Plant>()

    fun dataSync() : List<Plant> {
        val plant1 = Plant("Maggie the Montsera🌱", "Living Room")
        val plant2 = Plant("Huckleberry Fern 🌿", "Living Room")
        val plant3 = Plant("Indoor Basil III 🍃", "Kitchen")
        val plant4 = Plant("Dracaerys Flytrap 🐉", "Bedroom")
        val plant5 = Plant("Ouchie the Cactus 🌵", "Office")
        val plant6 = Plant("Don't give a Fig 🌱", "Living Room")
        val plant7 = Plant("Free Hugs the Cactus 🌵", "Office")
        val plant8 = Plant("Christofern 🌿", "Office")
        val plant9 = Plant("Lucky Bamboo-tiful 🎍", "Kitchen")
        val plant10 = Plant("Aloe Vera Wang 👗", "Balcony")
        val plant11 = Plant("Fig-alicious 🌾", "Living Room")
        val plant12 = Plant("Palmela Anderson 🌴", "Balcony")

        return listOf(
            plant1,
            plant2,
            plant3,
            plant4,
            plant5,
            plant6,
            plant7,
            plant8,
            plant9,
            plant10,
            plant11,
            plant12,
        )
    }
}