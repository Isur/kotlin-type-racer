package com.example.isur.typeracer.Model.Interface

import com.example.isur.typeracer.Model.DataModels.ScoreList

interface IScoreList {
    val SCORES: MutableList<ScoreList.Score>
    fun addScore(score: ScoreList.Score)
}