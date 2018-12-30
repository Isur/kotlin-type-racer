package com.example.isur.typeracer.Model.Interface

import com.example.isur.typeracer.Model.DataModels.ScoreList

interface IScoreList {
    val SCORES: MutableList<ScoreList.Score>
    val SCORE_MAP: MutableMap<Int, ScoreList.Score>
    fun addScore(score: ScoreList.Score)
}