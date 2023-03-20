package com.grandstream.sunflower.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.grandstream.sunflower.data.AppDatabase
import com.grandstream.sunflower.repository.PlantRepository
import com.grandstream.sunflower.utils.getPlantList

class MainWorker(val context: Context, workerParams: WorkerParameters): CoroutineWorker(context, workerParams) {
    private lateinit var plantRepository: PlantRepository

    override suspend fun doWork(): Result {
        Log.d(TAG, "input data = ${inputData.getString("time")}")
        plantRepository = PlantRepository(AppDatabase.getInstance(context).plantDao())
        if (plantRepository.getPlantSize() == 0) {
            plantRepository.insertAll(getPlantList())
        }
        val outputData = Data.Builder()
            .putString("name", "Jetpack")
            .build()
        return Result.success(outputData)
    }

    companion object {
        private const val TAG = "WorkManagerTest"
    }
}