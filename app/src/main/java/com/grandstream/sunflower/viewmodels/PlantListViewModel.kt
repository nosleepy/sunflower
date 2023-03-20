package com.grandstream.sunflower.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandstream.sunflower.entity.Plant
import com.grandstream.sunflower.repository.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantListViewModel @Inject constructor(private val plantRepository: PlantRepository): ViewModel() {
    val plants: StateFlow<List<Plant>> = plantRepository.getPlants().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList()
    )

    val addPlants: StateFlow<List<Plant>> = plantRepository.getAddPlants().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList()
    )

    fun insertAll(plants: List<Plant>) {
        viewModelScope.launch(Dispatchers.IO) {
            plantRepository.insertAll(plants)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            plantRepository.deleteAll()
        }
    }
}