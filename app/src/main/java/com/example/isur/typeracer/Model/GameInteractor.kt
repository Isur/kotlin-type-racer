package com.example.isur.typeracer.Model

import com.example.isur.typeracer.Model.DataModels.ScoreList
import com.example.isur.typeracer.Model.Interface.IGameInteractor
import com.example.isur.typeracer.Model.Repository.TypeRacerApi
import com.example.isur.typeracer.Model.Utils.Network.ConnectionInfo
import com.example.isur.typeracer.Model.Utils.Network.NoConnectivityException
import com.example.isur.typeracer.TypeRacerApplication
import com.example.isur.typeracer.Views.Interface.IGameBoard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class GameInteractor : IGameInteractor {
    private val apiService:TypeRacerApi = TypeRacerApi(TypeRacerApplication.provideConnectivityInterceptor())
    override fun getWord(): String {
        return try {runBlocking(Dispatchers.IO){
            apiService.getWord().await()
        }}catch (ex:NoConnectivityException){
            ConnectionInfo.sendNoConnection(TypeRacerApplication.applicationContext())
            return ""
        }
    }

    override fun getWords(): Array<String> {

        return try {runBlocking(Dispatchers.IO){
            apiService.getWords().await()
        }}catch (ex:NoConnectivityException){
            ConnectionInfo.sendNoConnection(TypeRacerApplication.applicationContext())
            return arrayOf("")
        }
    }

    override fun postScore(nickname: String, score: Int) {
        try {
            val response = runBlocking(Dispatchers.IO) {
                apiService.postScore(ScoreList.Score(nickname,score))
            }
        }catch (ex:NoConnectivityException){
            ConnectionInfo.sendNoConnection(TypeRacerApplication.applicationContext())

        }

    }

    override fun getGame(time: Int, context: IGameBoard): Game {
        return Game(time, context)
    }
}