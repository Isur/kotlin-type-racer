package com.example.isur.typeracer.Model.Utils.Network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

//this class is used for checking connection during api calls
class ConnectivityInterceptor(context: Context) : Interceptor {
    private val appContext = context
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!ConnectionInfo.isOnline(appContext)) {
            throw NoConnectivityException()
        }
        return chain.proceed(chain.request())
    }


}
class NoConnectivityException : IOException()
