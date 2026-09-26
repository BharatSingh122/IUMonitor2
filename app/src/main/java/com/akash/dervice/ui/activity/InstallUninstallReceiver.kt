package com.akash.dervice.ui.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast

class InstallUninstallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        val data: Uri? = intent.data
        val packageName = data?.schemeSpecificPart ?: "Unknown"

        var event = ""
        when (action) {
            Intent.ACTION_PACKAGE_ADDED -> event = "Installed"
            Intent.ACTION_PACKAGE_REMOVED -> event = "Uninstalled"
        }

        MainActivity.message = "$event: $packageName"
        Toast.makeText(context, packageName, Toast.LENGTH_SHORT).show()

        Log.e("TAG", "onReceive:-----------111111----- ", )
        val activityIntent = Intent(context.applicationContext, MainActivity::class.java)
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)

        context.applicationContext.startActivity(activityIntent)
    }
}
