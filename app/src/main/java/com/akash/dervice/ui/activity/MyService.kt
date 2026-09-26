package com.akash.dervice.ui.activity

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.*
import android.os.Build
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    private lateinit var receiver: InstallUninstallReceiver

    override fun onCreate() {
        super.onCreate()

        // Register receiver in code (required for Android 8+)
        receiver = InstallUninstallReceiver()
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_PACKAGE_ADDED)
            addAction(Intent.ACTION_PACKAGE_REMOVED)
            addDataScheme("package")
        }
        registerReceiver(receiver, filter)
        Log.e("TAG", "onCreate: -----------6666--")

            startForegroundService()
    }

    private fun startForegroundService() {
        val channelId = "install_uninstall_channel"
        val channelName = "Install/Uninstall Events"
        val manager = getSystemService(NotificationManager::class.java)
        Log.e("TAG", "onCreate: -----------77777--")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, channelName, NotificationManager.IMPORTANCE_LOW
            )
            manager.createNotificationChannel(channel)
        }

        val notification: Notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, channelId)
                .setContentTitle("Monitoring Apps")
                .setContentText("Watching for install/uninstall")
                .setSmallIcon(android.R.drawable.stat_sys_download_done)
                .build()
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        startForeground(1, notification)
    }

    override fun onBind(intent: Intent?): IBinder? = null
    override fun onDestroy() {
        super.onDestroy()
    }
}
