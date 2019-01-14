package com.example.isur.typeracer.Model.Interface

import com.example.isur.typeracer.Model.DataModels.ScoreList

interface IScoreInteractor {
    fun getScores(limit: Int): List<ScoreList.Score>
}