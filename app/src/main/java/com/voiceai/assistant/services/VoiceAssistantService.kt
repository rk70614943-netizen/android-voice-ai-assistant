package com.voiceai.assistant.services

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import com.voiceai.assistant.receivers.VoiceCommandReceiver
import com.voiceai.assistant.utils.AIResponseGenerator

class VoiceAssistantService : Service() {

    private var speechRecognizer: SpeechRecognizer? = null
    private val commandReceiver = VoiceCommandReceiver()

    override fun onCreate() {
        super.onCreate()
        setupVoiceRecognition()
        registerCommandReceiver()
    }

    private fun setupVoiceRecognition() {
        if (SpeechRecognizer.isRecognitionAvailable(this)) {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
            speechRecognizer?.setRecognitionListener(object : RecognitionListener {
                override fun onReadyForSpeech(params: Bundle?) {}
                override fun onBeginningOfSpeech() {}
                override fun onRmsChanged(rmsdB: Float) {}
                override fun onBufferReceived(buffer: ByteArray?) {}
                override fun onEndOfSpeech() {}
                override fun onError(error: Int) {}

                override fun onResults(results: Bundle?) {
                    val matches = results?.getStringArrayList(
                        SpeechRecognizer.RESULTS_RECOGNITION
                    )
                    if (matches != null && matches.isNotEmpty()) {
                        processCommand(matches[0])
                    }
                }

                override fun onPartialResults(partialResults: Bundle?) {}
                override fun onEvent(eventType: Int, params: Bundle?) {}
            })
        }
    }

    private fun registerCommandReceiver() {
        val filter = IntentFilter("com.voiceai.START_LISTENING")
        registerReceiver(commandReceiver, filter)
    }

    private fun processCommand(command: String) {
        val response = AIResponseGenerator.generateResponse(this, command)
        val intent = Intent("com.voiceai.RESPONSE")
        intent.putExtra("response", response)
        sendBroadcast(intent)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer?.destroy()
        unregisterReceiver(commandReceiver)
    }
}
