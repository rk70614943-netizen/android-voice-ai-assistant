package com.voiceai.assistant.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.work.BackoffPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.voiceai.assistant.workers.TaskAutomationWorker
import java.util.concurrent.TimeUnit

class TaskAutomationService : Service() {

    override fun onCreate() {
        super.onCreate()
        scheduleAutomationTasks()
    }

    private fun scheduleAutomationTasks() {
        val taskRequest = OneTimeWorkRequestBuilder<TaskAutomationWorker>()
            .setBackoffPolicy(BackoffPolicy.EXPONENTIAL, 1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(this).enqueueUniqueWork(
            "task_automation",
            ExistingWorkPolicy.KEEP,
            taskRequest
        )
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
