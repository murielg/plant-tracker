package com.gonzoapps.planttracker.screens.myplants

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gonzoapps.planttracker.db.PlantDatabaseDao

class PlantsViewModelFactory(
    private val dataSource: PlantDatabaseDao, private val application: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantsViewModel::class.java)) {
            return PlantsViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}