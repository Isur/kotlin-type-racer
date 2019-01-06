package com.example.isur.typeracer.Model

import com.example.isur.typeracer.Model.Interface.IGameInteractor
import com.example.isur.typeracer.Model.Repository.MockAPI
import com.example.isur.typeracer.Model.Utils.JSONParser
import com.example.isur.typeracer.Views.Interface.IGameBoard

class GameInteractor : IGameInteractor {
    val API = MockAPI
    override fun getWord(): String {
        return API.getWord()
    }

    override fun getWords(): Array<String> {
        val response = API.getWords()
        return JSONParser.jsonToArray(response)
    }

    override fun postScore(nickname: String, score: Int) {
        val jsonString = JSONParser.scoreToJson(nickname, score)
        API.postScore(jsonString)
        API.postScore(nickname, score) // TODO("Remove when jsonParser implemented")
    }

    override fun getGame(time: Int, context: IGameBoard): Game {
        return Game(time, context)
    }
}