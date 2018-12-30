package com.example.isur.typeracer.Model

import com.example.isur.typeracer.Model.Interface.IGameInteractor
import com.example.isur.typeracer.Model.Repository.MockAPI

class GameInteractor : IGameInteractor {
    val API = MockAPI
    override fun getWord(): String {
        return API.getWord()
    }

    override fun getWords(): Array<String> {
        return API.getWords()
    }

    override fun postScore(nickname: String, score: Int) {
        TODO("not implemented")
    }
}