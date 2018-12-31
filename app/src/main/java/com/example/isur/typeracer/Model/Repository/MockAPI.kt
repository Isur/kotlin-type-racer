package com.example.isur.typeracer.Model.Repository

import com.example.isur.typeracer.Model.DataModels.MockData
import com.example.isur.typeracer.Model.DataModels.ScoreList
import com.example.isur.typeracer.Model.Interface.IAPI

object MockAPI: IAPI {
    override fun getWord(): String {
        return "randomWORDtest"
    }

    override fun getWords(): Array<String>{
        return arrayOf("rand1", "rand2", "rand3","rand4", "rand5")
    }

    override fun getScores():ScoreList {
        val scores = MockData.scoreList
        if (scores.SCORES.size == 0) {
            for (i in 1..10) {
                val nickName = "player$i"
                val score = i * 10
                scores.addScore(ScoreList.Score(score, nickName))
            }
        }
        return scores
    }

    override fun postScore(nickname: String, score: Int) {
        MockData.scoreList.addScore(ScoreList.Score(score, nickname))
    }
}