package com.example.isur.typeracer.Model

import com.example.isur.typeracer.Model.DataModels.ScoreList
import com.example.isur.typeracer.Model.Interface.IGameInteractor
import com.example.isur.typeracer.Model.Repository.TypeRacerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class GameInteractor : IGameInteractor {
    val apiService = TypeRacerApi()
    override fun getWord(): String {
        val response = runBlocking (Dispatchers.IO){
            apiService.getWord().await()
        }
        return response
    }

    override fun getWords(): Array<String> {

        val response = runBlocking (Dispatchers.IO){
            apiService.getWords().await()
        }
        return arrayOf(response)
    }

    override fun postScore(nickname: String, score: Int) {

        val response =
        runBlocking(Dispatchers.IO) {

            apiService.postScore(ScoreList.Score(nickname,score))

        }

    }
}