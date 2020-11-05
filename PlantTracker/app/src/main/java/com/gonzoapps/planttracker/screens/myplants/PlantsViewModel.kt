package com.gonzoapps.planttracker.screens.myplants

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.gonzoapps.planttracker.db.PlantDatabaseDao
import com.gonzoapps.planttracker.models.Plant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class PlantsViewModel(private val dataSource: PlantDatabaseDao, application: Application) : AndroidViewModel(application) {

    val plantName = MutableLiveData<String>()
    val plantLocation = MutableLiveData<String>()
    val plantCare = MutableLiveData<String>()
    val plantLog = MutableLiveData<String>()

    val allPlants = dataSource.getAllPlants()

    private val _newPlantAdded = MutableLiveData<Boolean>()
    val newPlantAdded: LiveData<Boolean>
        get() = _newPlantAdded

    init {
        _newPlantAdded.value = false
    }

    fun onAddNewPlantButtonClicked() {
        _newPlantAdded.value = false
    }

    fun onCancelClicked() {
        _newPlantAdded.value = false
    }

    fun addNewPlant() {
        viewModelScope.launch {
            val plant = Plant(
                plantName.value.toString(),
                plantLocation.value.toString(),
                plantCare.value.toString(),
                plantLog.value.toString(),
            )
            insertPlant(plant)

            resetFields()
            _newPlantAdded.value = true
        }
    }

    private suspend fun insertPlant(plant: Plant) {
        withContext(Dispatchers.IO) {
            dataSource.insert(plant)
        }
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