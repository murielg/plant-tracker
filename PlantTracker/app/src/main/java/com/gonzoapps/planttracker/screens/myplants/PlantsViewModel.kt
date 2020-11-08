package com.gonzoapps.planttracker.screens.myplants

import android.app.Application
import androidx.lifecycle.*
import com.gonzoapps.planttracker.db.PlantDatabaseDao
import timber.log.Timber

class PlantsViewModel(private val dataSource: PlantDatabaseDao) :  ViewModel() {

    val allPlants = dataSource.getAllPlants()

    private val _navigateToPlantDetail = MutableLiveData<String>()
    val navigateToPlantDetail: LiveData<String>
        get() = _navigateToPlantDetail

    fun onPlantClicked(id: String){
        _navigateToPlantDetail.value = id
    }

    fun onPlantDetailNavigated() {
        _navigateToPlantDetail.value = null
    }

}