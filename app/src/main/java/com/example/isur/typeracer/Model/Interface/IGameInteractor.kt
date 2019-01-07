package com.example.isur.typeracer.Model.Interface

import com.example.isur.typeracer.Model.Game
import com.example.isur.typeracer.Views.Interface.IGameBoard

interface IGameInteractor {
    fun getWord():String
    fun getWords():Array<String>
    fun postScore(nickname: String, score: Int)
    fun getGame(time: Int, context: IGameBoard): Game
}