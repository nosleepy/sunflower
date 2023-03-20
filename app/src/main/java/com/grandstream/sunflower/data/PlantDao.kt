package com.grandstream.sunflower.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.grandstream.sunflower.entity.Plant
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {
    @Query("SELECT * FROM plants ORDER BY name")
    fun getPlants(): Flow<List<Plant>>

    @Query("SELECT * FROM plants WHERE flag = '1' ORDER BY name")
    fun getAddPlants(): Flow<List<Plant>>

    @Query("SELECT * FROM plants where id = :plantId")
    fun getPlant(plantId: Int?): LiveData<Plant>

    @Query("SELECT COUNT(*) FROM plants")
    fun getPlantSize(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(plants: List<Plant>)

    @Query("DELETE FROM plants")
    fun deleteAll()

    @Query("UPDATE plants SET flag = 1 WHERE id = :plantId")
    fun addPlant(plantId: Int)

    @Query("UPDATE plants SET flag = 0 WHERE id = :plantId")
    fun deletePlant(plantId: Int)

    @Update
    fun updatePlant(plant: Plant)
}