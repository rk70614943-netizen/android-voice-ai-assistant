package com.voiceai.assistant.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.voiceai.assistant.R
import com.voiceai.assistant.services.VoiceAssistantService

class MainActivity : AppCompatActivity() {

    private lateinit var voiceButton: Button
    private lateinit var statusText: TextView
    private lateinit var responseText: TextView
    private val PERMISSION_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        voiceButton = findViewById(R.id.voice_button)
        statusText = findViewById(R.id.status_text)
        responseText = findViewById(R.id.response_text)

        // Check and request permissions
        checkPermissions()

        voiceButton.setOnClickListener {
            startListening()
        }

        // Start background service
        startService(Intent(this, VoiceAssistantService::class.java))
    }

    private fun checkPermissions() {
        val permissions = arrayOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.INTERNET,
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_CALENDAR
        )

        val permissionsToRequest = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun startListening() {
        voiceButton.isEnabled = false
        statusText.text = "🎤 Listening..."
        statusText.setTextColor(resources.getColor(android.R.color.holo_blue_bright))

        val intent = Intent("com.voiceai.START_LISTENING")
        sendBroadcast(intent)
    }

    fun updateResponse(response: String) {
        runOnUiThread {
            responseText.text = response
            voiceButton.isEnabled = true
            statusText.text = "✅ Ready"
            statusText.setTextColor(resources.getColor(android.R.color.holo_green_light))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            val allGranted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
            if (allGranted) {
                statusText.text = "✅ All permissions granted"
            } else {
                statusText.text = "⚠️ Some permissions denied"
            }
        }
    }
}
