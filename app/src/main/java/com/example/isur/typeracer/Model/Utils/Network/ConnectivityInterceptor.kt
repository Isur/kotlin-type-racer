package com.example.isur.typeracer.Model.Utils.Network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

//class ConnectivityInterceptor(context: Context):Interceptor {
//    private val appContext = context.applicationContext
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        if (!isOnline())
//            throw NoConnectivityException()
//        return chain.proceed(chain.request())
//    }
//
//    private fun isOnline(): Boolean {
//        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
//                as ConnectivityManager
//        val networkInfo = connectivityManager.activeNetworkInfo
//        return networkInfo != null && networkInfo.isConnected
//    }
//}