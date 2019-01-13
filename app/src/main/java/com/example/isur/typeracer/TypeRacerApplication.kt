package com.example.isur.typeracer

import android.app.Application
import android.content.Context
import com.example.isur.typeracer.Model.Utils.Network.ConnectionInfo
import com.example.isur.typeracer.Model.Utils.Network.ConnectivityInterceptor

class TypeRacerApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: TypeRacerApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

        fun provideConnectivityInterceptor() = ConnectivityInterceptor(applicationContext())
    }

    override fun onCreate() {
        super.onCreate()
        val context: Context = TypeRacerApplication.applicationContext()
        if (!ConnectionInfo.isOnline(context)) {
            ConnectionInfo.sendNoConnection(context)
        }
    }
}