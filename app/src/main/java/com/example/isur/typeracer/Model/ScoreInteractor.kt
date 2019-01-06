package com.example.isur.typeracer.Model

import com.example.isur.typeracer.Model.DataModels.ScoreList
import com.example.isur.typeracer.Model.Interface.IScoreInteractor
import com.example.isur.typeracer.Model.Repository.MockAPI
import com.example.isur.typeracer.Model.Repository.TypeRacerApi
import com.example.isur.typeracer.Model.Utils.JSONParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking


class ScoreInteractor:IScoreInteractor{
    val API = MockAPI
    val apiService = TypeRacerApi()
    override fun getScores(limit: Int): List<ScoreList.Score> {
       // val response = API.getScores()
        val response = runBlocking (Dispatchers.IO){
            apiService.getTop10().await()
        }
        return response.take(limit)
    }

}