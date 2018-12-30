package com.example.isur.typeracer.Model.DataModels

import com.example.isur.typeracer.Model.Interface.IScore
import com.example.isur.typeracer.Model.Interface.IScoreList

class ScoreList:IScoreList {
    override val SCORES: MutableList<Score> = ArrayList()
    override val SCORE_MAP: MutableMap<Int, Score> = HashMap()

    override fun addScore(score: Score){
        SCORES.add(score)
        SCORE_MAP[score.position] = score
    }

    data class Score(override val position: Int, override val score: Int, override val nick: String): IScore
}