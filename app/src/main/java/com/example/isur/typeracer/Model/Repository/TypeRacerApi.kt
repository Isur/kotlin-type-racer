package com.example.isur.typeracer.Model.Repository

import com.example.isur.typeracer.Model.DataModels.ScoreList
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TypeRacerApi {

    @GET("getWord")
    fun getWord(): Deferred<String>
    @GET("getWords")
    fun getWords(): Deferred<String>
    @GET("top10")
    fun getTop10():Deferred<List<ScoreList.Score>>
    @POST("result")
    fun postScore(@Body result:ScoreList.Score)
    companion object {
        operator fun invoke(): TypeRacerApi {
            val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://simple-type-racer.herokuapp.com/server/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(TypeRacerApi::class.java)
        }
    }
}