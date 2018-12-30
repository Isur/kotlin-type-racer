package com.example.isur.typeracer.Model.Interface

interface IGameInteractor {
    fun getWord():String
    fun getWords():Array<String>
    fun postScore(nickname: String, score: Int)

}