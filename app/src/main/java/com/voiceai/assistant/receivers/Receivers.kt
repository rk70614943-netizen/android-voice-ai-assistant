package com.voiceai.assistant.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer

class VoiceCommandReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "com.voiceai.START_LISTENING") {
            // Voice listening logic will be triggered
        }
    }
}

class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            val serviceIntent = Intent(context, com.voiceai.assistant.services.VoiceAssistantService::class.java)
            context?.startService(serviceIntent)
        }
    }
}
