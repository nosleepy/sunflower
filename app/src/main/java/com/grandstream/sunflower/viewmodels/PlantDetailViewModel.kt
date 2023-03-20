package com.grandstream.sunflower.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandstream.sunflower.entity.Plant
import com.grandstream.sunflower.repository.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantDetailViewModel @Inject constructor(private val plantRepository: PlantRepository): ViewModel() {
    fun getPlant(plantId: Int): LiveData<Plant> {
        return plantRepository.getPlant(plantId)
    }

    fun addPlant(plantId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            plantRepository.addPlant(plantId)
        }
    }

    fun deletePlant(plantId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            plantRepository.deletePlant(plantId)
        }
    }

    fun updatePlant(plant: Plant) {
        viewModelScope.launch(Dispatchers.IO) {
            plantRepository.updatePlant(plant)
        }
    }
}