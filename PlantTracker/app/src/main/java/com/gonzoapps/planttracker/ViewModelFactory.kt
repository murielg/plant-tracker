package com.gonzoapps.planttracker

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.gonzoapps.planttracker.db.PlantDatabaseDao
import com.gonzoapps.planttracker.screens.detail.PlantDetailViewModel
import com.gonzoapps.planttracker.screens.myplants.PlantsViewModel

class ViewModelFactory(
        private val datasource: PlantDatabaseDao,
        owner: SavedStateRegistryOwner,
        defaultArgs: Bundle? = null
): AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(PlantsViewModel::class.java) ->
                PlantsViewModel(datasource)
            isAssignableFrom(PlantDetailViewModel::class.java) ->
                PlantDetailViewModel(datasource)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}