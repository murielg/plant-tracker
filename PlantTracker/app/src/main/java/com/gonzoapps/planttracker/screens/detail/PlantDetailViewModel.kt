package com.gonzoapps.planttracker.screens.detail

import androidx.lifecycle.*
import com.gonzoapps.planttracker.db.PlantDatabaseDao
import com.gonzoapps.planttracker.models.Plant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class PlantDetailViewModel(private val dataSource: PlantDatabaseDao) : ViewModel() {

    private var plantId: String? = null

    private var isNewPlant: Boolean = false

    // Two-way databinding, exposing MutableLiveData
    val name = MutableLiveData<String>()
    val location = MutableLiveData<String>()
    val care = MutableLiveData<String>()
    val log = MutableLiveData<String>()

    private val _navigateToPlantList = MutableLiveData<Boolean>()
    val navigateToPlantList: LiveData<Boolean>
        get() = _navigateToPlantList

    private val _snackbarText = MutableLiveData<String>()
    val snackbarText: LiveData<String> = _snackbarText

    init {
        Timber.i("PlantDetailViewModel - init called")
        _navigateToPlantList.value = false
    }


    fun start(plantId: String?) {
        this.plantId = plantId;

        if (plantId == null) {
            isNewPlant = true
            return
        }

        isNewPlant = false

        viewModelScope.launch {
            getPlantFromDatabase(plantId)
        }
    }


    private suspend fun getPlantFromDatabase(id: String) {
        val dbPlant = withContext(Dispatchers.IO) {
           dataSource.get(id)
        }
        this.name.value = dbPlant?.name
        this.location.value = dbPlant?.location
        this.care.value = dbPlant?.careInstructions
        this.log.value = dbPlant?.log

    }

    fun savePlant() {
        val currentName = name.value.toString()
        val currentLocation = location.value.toString()
        val currentCare = care.value.toString()
        val currentLog = log.value.toString()

        if (currentName.isEmpty() || currentLocation.isEmpty()) {
            _snackbarText.value = "Please fill out Name and Location"
            return
        }

        val currentPlantId = plantId

        if (isNewPlant || currentPlantId == null) {
            insertNewPlant(Plant(currentName,currentLocation,currentCare, currentLog))
        } else {
            updateExistingPlant(Plant(currentName,currentLocation,currentCare, currentLog, currentPlantId))
        }

    }

    private fun updateExistingPlant(plant: Plant) {
        viewModelScope.launch {
            updatePlantDB(plant)
            resetFields()
            _navigateToPlantList.value = true
        }

    }
    private suspend fun updatePlantDB(plant: Plant) {
        withContext(Dispatchers.IO) {
            dataSource.update(plant)
        }
    }

    private fun insertNewPlant(newPlant: Plant) {
        viewModelScope.launch {
            insertPlant(newPlant)
            resetFields()
            _navigateToPlantList.value = true
        }

    }
    private suspend fun insertPlant(plant: Plant) {
        withContext(Dispatchers.IO) {
            dataSource.insert(plant)
        }
    }

    fun onCancelClicked() {
        _navigateToPlantList.value = true
    }

    private fun resetFields() {
        name.value = ""
        location.value = ""
        care.value = ""
        log.value = ""
    }

}