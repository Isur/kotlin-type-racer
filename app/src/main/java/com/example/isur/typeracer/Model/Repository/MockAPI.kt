package com.example.isur.typeracer.Model.Repository

import com.example.isur.typeracer.Model.DataModels.MockData
import com.example.isur.typeracer.Model.DataModels.ScoreList
import com.example.isur.typeracer.Model.Interface.IAPI

object MockAPI: IAPI {
    override fun getScores(): String {
        // just for interface
        return ""
    }

    override fun postScore(jsonString: String) {
        // just for interface
    }

    override fun getWord(): String {
        return "randomWORDtest"
    }

    override fun getWords(): String {
        return "[\"word1\", \"word2\", \"word3\", \"word4\", \"word5\"]"
    }

    fun getScoresMock(): ScoreList {
        val scores = MockData.scoreList
        if (scores.SCORES.size == 0) {
            for (i in 1..10) {
                val nickName = "player$i"
                val score = i * 10
                scores.addScore(ScoreList.Score(nickName,score))
            }
        }
        return scores
    }

    fun postScore(nickname: String, score: Int) {
        MockData.scoreList.addScore(ScoreList.Score(nickname,score))
    }
}