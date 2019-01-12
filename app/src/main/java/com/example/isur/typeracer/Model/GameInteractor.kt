package com.example.isur.typeracer.Model

import com.example.isur.typeracer.Model.DataModels.ScoreList
import com.example.isur.typeracer.Model.Interface.IGameInteractor
import com.example.isur.typeracer.Model.Repository.TypeRacerApi
import com.example.isur.typeracer.Model.Utils.Network.ConnectivityInterceptor
import com.example.isur.typeracer.TypeRacerApplication
import com.example.isur.typeracer.Views.Interface.IGameBoard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class GameInteractor : IGameInteractor {
    private val apiService:TypeRacerApi = TypeRacerApi(TypeRacerApplication.provideConnectivityInterceptor())
    override fun getWord(): String {
        val response = runBlocking (Dispatchers.IO){
            apiService.getWord().await()
        }
        return response
    }

    override fun getWords(): Array<String> {

        return runBlocking(Dispatchers.IO){
            apiService.getWords().await()
        }
    }

    override fun postScore(nickname: String, score: Int) {
        val response = runBlocking(Dispatchers.IO) {
            apiService.postScore(ScoreList.Score(nickname,score))
        }
    }

    override fun getGame(time: Int, context: IGameBoard): Game {
        return Game(time, context)
    }
}