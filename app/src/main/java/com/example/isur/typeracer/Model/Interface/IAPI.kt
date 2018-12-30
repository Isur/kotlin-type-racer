package com.example.isur.typeracer.Model.Interface

import com.example.isur.typeracer.Model.DataModels.ScoreList

interface IAPI {
    fun getWord():String
    fun getWords():Array<String>
    fun getScores():ScoreList
    fun postScore(nickname: String, score: Int)
}