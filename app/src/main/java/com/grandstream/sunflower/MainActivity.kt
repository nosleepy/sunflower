package com.grandstream.sunflower

import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.grandstream.sunflower.worker.MainWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dateFormat = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
        val data = Data.Builder()
            .putString("time", dateFormat.format(Date()))
            .build()
        val request = OneTimeWorkRequest.Builder(MainWorker::class.java)
            .setInputData(data)
            .build()
        WorkManager.getInstance(this).enqueue(request)
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
            .observe(this) {
                Log.d(TAG, "state = ${it.state}")
                if (it.state == WorkInfo.State.SUCCEEDED) {
                    Log.d(TAG, "output data = ${it.outputData.getString("name")}")
                }
            }
//        WorkManager.getInstance(this).cancelWorkById(request.id) //取消任务
    }

    companion object {
        private const val TAG = "WorkManagerTest"
    }
}