package com.example.isur.typeracer.Model.DataModels

import com.example.isur.typeracer.Model.Interface.IScore
import com.example.isur.typeracer.Model.Interface.IScoreList
import com.squareup.moshi.Json

class ScoreList:IScoreList {
    override val SCORES: MutableList<Score> = ArrayList()

    override fun getSorted(limit: Int): List<ScoreList.Score> {
        return SCORES.sortedByDescending { it.score }.take(limit)
    }

    override fun addScore(score: Score){
        SCORES.add(score)
    }

    data class Score(
        @Json(name = "nickname")
        override val nickname: String,
        @Json(name = "score")
        override val score: Int) : IScore
}