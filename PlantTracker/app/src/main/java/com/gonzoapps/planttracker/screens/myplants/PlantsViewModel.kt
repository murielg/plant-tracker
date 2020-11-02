package com.gonzoapps.planttracker.screens.myplants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gonzoapps.planttracker.models.Plant
import timber.log.Timber

class PlantsViewModel : ViewModel() {

    private val _plants = MutableLiveData<MutableList<Plant>>()

    val plants : LiveData<MutableList<Plant>>
        get() = _plants

    fun addNewPlant(plant: Plant) {
        _plants.value?.add(0,plant)
    }

    init {
        _plants.value = MockPlantProvider.dataSync()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared called")
    }
}