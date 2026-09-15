package com.voiceai.assistant.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [AutomationTask::class, VoiceCommand::class],
    version = 1,
    exportSchema = false
)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun voiceCommandDao(): VoiceCommandDao

    companion object {
        @Volatile
        private var instance: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "voice_assistant_db"
                ).build().also { instance = it }
            }
        }
    }
}
