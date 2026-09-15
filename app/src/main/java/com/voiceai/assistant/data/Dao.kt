package com.voiceai.assistant.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Query("SELECT * FROM automation_tasks")
    suspend fun getAllTasks(): List<AutomationTask>

    @Query("SELECT * FROM automation_tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: Int): AutomationTask?

    @Insert
    suspend fun insertTask(task: AutomationTask)

    @Update
    suspend fun updateTask(task: AutomationTask)

    @Delete
    suspend fun deleteTask(task: AutomationTask)
}

@Dao
interface VoiceCommandDao {
    @Query("SELECT * FROM voice_commands ORDER BY timestamp DESC LIMIT 50")
    suspend fun getRecentCommands(): List<VoiceCommand>

    @Insert
    suspend fun insertCommand(command: VoiceCommand)

    @Query("DELETE FROM voice_commands WHERE timestamp < :timeLimit")
    suspend fun deleteOldCommands(timeLimit: Long)
}
