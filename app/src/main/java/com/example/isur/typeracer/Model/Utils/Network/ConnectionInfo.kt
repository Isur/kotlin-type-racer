package com.example.isur.typeracer.Model.Utils.Network

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.example.isur.typeracer.Model.Repository.TypeRacerApi
import com.example.isur.typeracer.TypeRacerApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

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
    fun checkServerStatus():Boolean{
        val api : TypeRacerApi = TypeRacerApi(TypeRacerApplication.provideConnectivityInterceptor())
        return try{
            val response = runBlocking(Dispatchers.IO) {
                api.getConnection().await()
            }
            response.connection
        }catch (ex:NoConnectivityException){
            sendNoConnection(TypeRacerApplication.applicationContext())
            false
        }
    }
}