package com.example.isur.typeracer.Model.Interface

import com.example.isur.typeracer.Model.DataModels.ScoreList

interface IAPI {
    fun getWord():String
    fun getWords(): String
    fun getScores(): String
    fun postScore(jsonString: String)
}