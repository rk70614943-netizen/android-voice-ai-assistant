package com.voiceai.assistant.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "automation_tasks")
data class AutomationTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val type: String,
    val description: String,
    val interval: Long,
    val lastExecuted: Long = 0,
    val enabled: Boolean = true
)

@Entity(tableName = "voice_commands")
data class VoiceCommand(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val command: String,
    val response: String,
    val timestamp: Long = System.currentTimeMillis()
)
