package com.gonzoapps.planttracker.screens.myplants

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gonzoapps.planttracker.models.Plant
import timber.log.Timber

class PlantsViewModel : ViewModel() {

    val plantName = MutableLiveData<String>()
    val plantLocation = MutableLiveData<String>()
    val plantCare = MutableLiveData<String>()
    val plantLog = MutableLiveData<String>()

    private val _plants = MutableLiveData<MutableList<Plant>>()

    val plants : LiveData<MutableList<Plant>>
        get() = _plants

    private val _newPlantAdded = MutableLiveData<Boolean>()
    val newPlantAdded: LiveData<Boolean>
        get() = _newPlantAdded

    init {
        _plants.value = MockPlantProvider.dataSync()
        _newPlantAdded.value = false
    }

    fun onAddNewPlantButtonClicked() {
        _newPlantAdded.value = false
    }

    fun onCancelClicked() {
        _newPlantAdded.value = false
    }

    fun addNewPlant() {
        val plant = Plant(
            plantName.value.toString(),
            plantLocation.value.toString(),
            plantCare.value.toString(),
            plantLog.value.toString(),
        )
        _plants.value?.add(0, plant)
        resetFields()
        _newPlantAdded.value = true
    }

    private fun resetFields() {
        plantName.value = ""
        plantLocation.value = ""
        plantCare.value = ""
        plantLog.value = ""
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared called")
    }
}