package com.example.isur.typeracer.Model.DataModels

import com.example.isur.typeracer.Model.Interface.IScore
import com.example.isur.typeracer.Model.Interface.IScoreList

class ScoreList:IScoreList {
    override val SCORES: MutableList<Score> = ArrayList()

    override fun addScore(score: Score){
        SCORES.add(score)
    }

    data class Score(override val score: Int, override val nick: String) : IScore
}