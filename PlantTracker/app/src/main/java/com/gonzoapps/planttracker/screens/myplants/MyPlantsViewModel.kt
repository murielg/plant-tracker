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


    val plant = Plant(
        "Montsera",
        1234,
        "Living Room",
        "Water every other day.",
        "Empty"
    )


    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared called")
    }
}