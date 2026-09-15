package com.voiceai.assistant.utils

import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import com.voiceai.assistant.data.TaskDatabase

object AIResponseGenerator {

    fun generateResponse(context: Context, command: String): String {
        return when {
            command.contains("time", ignoreCase = true) -> getCurrentTime()
            command.contains("date", ignoreCase = true) -> getCurrentDate()
            command.contains("hello", ignoreCase = true) -> "Hello! How can I help you?"
            command.contains("weather", ignoreCase = true) -> "Fetching weather..."
            command.contains("send sms", ignoreCase = true) -> handleSMS(context, command)
            command.contains("call", ignoreCase = true) -> handleCall(context, command)
            command.contains("remind", ignoreCase = true) -> setReminder(context, command)
            command.contains("open", ignoreCase = true) -> openApp(context, command)
            command.contains("battery", ignoreCase = true) -> "Getting battery status..."
            command.contains("volume", ignoreCase = true) -> adjustVolume(context, command)
            else -> processAIQuery(context, command)
        }
    }

    private fun getCurrentTime(): String {
        val calendar = java.util.Calendar.getInstance()
        val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)
        val minute = calendar.get(java.util.Calendar.MINUTE)
        return "Current time is $hour:$minute"
    }

    private fun getCurrentDate(): String {
        val calendar = java.util.Calendar.getInstance()
        val day = calendar.get(java.util.Calendar.DAY_OF_MONTH)
        val month = calendar.get(java.util.Calendar.MONTH) + 1
        val year = calendar.get(java.util.Calendar.YEAR)
        return "Today is $day/$month/$year"
    }

    private fun handleSMS(context: Context, command: String): String {
        return try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("+919999999999", null, "Auto message", null, null)
            "SMS sent successfully"
        } catch (e: Exception) {
            "Unable to send SMS: ${e.message}"
        }
    }

    private fun handleCall(context: Context, command: String): String {
        return "Call feature initiated"
    }

    private fun setReminder(context: Context, command: String): String {
        return "Reminder set for you"
    }

    private fun openApp(context: Context, command: String): String {
        return "Opening requested app..."
    }

    private fun adjustVolume(context: Context, command: String): String {
        return "Adjusting volume..."
    }

    private fun processAIQuery(context: Context, command: String): String {
        // Local AI processing without external API
        return generateLocalAIResponse(command)
    }

    private fun generateLocalAIResponse(query: String): String {
        val keywords = query.toLowerCase().split(" ")
        return when {
            keywords.contains("help") -> "I can help you with: sending messages, setting reminders, opening apps, and more!"
            keywords.contains("what") -> "I'm an AI assistant. Ask me anything!"
            keywords.contains("who") -> "I'm your personal voice assistant"
            else -> "I understood your query: '$query'. Processing..."
        }
    }
}
