package com.gonzoapps.planttracker.screens.myplants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gonzoapps.planttracker.models.Plant
import timber.log.Timber

class MyPlantsViewModel : ViewModel() {

    private val _plants = MutableLiveData<List<Plant>>()

    val plants : LiveData<List<Plant>>
        get() = _plants


    val plant1 = Plant("montsera")
    val plant2 = Plant("baby cactus")
    val plant3 = Plant("indoor basil")

    init {
        _plants.value = listOf(
            plant1, plant2, plant3,plant1, plant2, plant3
        )
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared called")
    }
}