package com.grandstream.sunflower.repository

import androidx.lifecycle.LiveData
import com.grandstream.sunflower.data.PlantDao
import com.grandstream.sunflower.entity.Plant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantRepository @Inject constructor(private val plantDao: PlantDao) {
    fun getPlants() = plantDao.getPlants()

    fun getAddPlants() = plantDao.getAddPlants()

    fun getPlant(plantId: Int): LiveData<Plant> = plantDao.getPlant(plantId)

    fun getPlantSize(): Int = plantDao.getPlantSize()

    fun insertAll(plants: List<Plant>) = plantDao.insertAll(plants)

    fun deleteAll() = plantDao.deleteAll()

    fun addPlant(plantId: Int) = plantDao.addPlant(plantId)

    fun deletePlant(plantId: Int) = plantDao.deletePlant(plantId)

    fun updatePlant(plant: Plant) = plantDao.updatePlant(plant)
}