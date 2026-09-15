# Android AI Voice Assistant - Troubleshooting Guide

## Common Issues and Solutions

### 1. Microphone Not Detected

**Problem**: App crashes when accessing microphone

**Solutions**:
```
✓ Go to Settings > Apps > Voice AI Assistant > Permissions
✓ Enable Microphone permission
✓ Check if another app is using microphone
✓ Restart device
```

### 2. Voice Commands Not Recognized

**Problem**: Voice input not converting to text

**Solutions**:
```
✓ Check internet connection (required for speech recognition)
✓ Speak clearly and slowly
✓ Check ambient noise level
✓ Ensure Google Play Services is updated
✓ Try in quiet environment
```

### 3. Tasks Not Executing

**Problem**: SMS/Call/Reminder commands not working

**Solutions**:
```
✓ Verify all permissions granted
✓ Check battery optimization settings
✓ Disable Doze mode for app
✓ Restart the application
✓ Check task automation service is running
```

### 4. Database Errors

**Problem**: "Database error" messages

**Solutions**:
```
✓ Clear app data: Settings > Apps > Voice AI Assistant > Storage > Clear All
✓ Reinstall application
✓ Check storage space (need 50MB minimum)
```

### 5. App Crashes on Startup

**Problem**: App crashes immediately after opening

**Solutions**:
```
✓ Clear app cache
✓ Update Android Studio and dependencies
✓ Reinstall the APK
✓ Check Android version compatibility (min API 24)
```

### 6. Slow Response Time

**Problem**: AI takes too long to respond

**Solutions**:
```
✓ Check internet connection speed
✓ Close background apps
✓ Restart device
✓ Check device RAM availability
```

## Advanced Troubleshooting

### Enable Debug Logging

```kotlin
// Add to MainActivity.kt for debugging
android.util.Log.d("VoiceAI", "Debug message")
```

### Check Service Status

```bash
# Check if service is running
adb shell dumpsys activity services | grep VoiceAssistantService
```

### View Database

```bash
# Access database file
adb shell
cd /data/data/com.voiceai.assistant/databases
sqlite3 voice_assistant_db
.tables
```

## Performance Optimization

### Reduce Battery Drain

1. **Adjust Task Automation Interval**
   - Edit `TaskAutomationWorker.kt`
   - Increase interval value

2. **Disable Unnecessary Permissions**
   - Only enable required permissions
   - Disable location access if not needed

3. **Optimize Database**
   - Clear old voice commands regularly
   - Archive completed tasks

### Improve Response Speed

1. **Reduce Network Latency**
   - Use WiFi instead of mobile data
   - Check signal strength

2. **Clear Cache Regularly**
   - Settings > Apps > Voice AI Assistant > Storage > Clear Cache

3. **Disable Battery Saver Mode**
   - Can limit app functionality

## Getting Help

### Resources
- [GitHub Issues](https://github.com/rk70614943-netizen/android-voice-ai-assistant/issues)
- [Android Developer Docs](https://developer.android.com/)
- [Google ML Kit Documentation](https://developers.google.com/ml-kit)

### Reporting Bugs

When reporting issues, include:
1. Android version and device model
2. App version number
3. Steps to reproduce the issue
4. Error messages (from logcat)
5. Screenshots

```bash
# Capture logs for bug report
adb logcat > bug_report.txt
```

## FAQs

**Q: Does the app send data to cloud?**
A: No, all data is stored locally on your device.

**Q: Can I use offline?**
A: Most features need internet, but local commands work offline.

**Q: Is my privacy protected?**
A: Yes, complete privacy-first approach with local storage.

**Q: Can I customize commands?**
A: Yes, edit `AIResponseGenerator.kt` for custom commands.

**Q: How much storage does it use?**
A: Approximately 50MB for installation + database size.
