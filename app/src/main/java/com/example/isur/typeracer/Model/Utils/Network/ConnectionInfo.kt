package com.example.isur.typeracer.Model.Utils.Network

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

object ConnectionInfo{
    const val CONNECTION_ACTION = "connection.intent.MAIN"
    fun sendNoConnection(context: Context){
        context.sendBroadcast(Intent(CONNECTION_ACTION))
    }
     fun isOnline(appContext: Context): Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}