package com.example.isur.typeracer.Model.Repository

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
        val scores = ScoreList()
        for(i in 1..10){
            val nickName = "player$i"
            val score = i*10
            scores.addScore(ScoreList.Score(i, score, nickName))
        }
        return scores
    }

    override fun postScore(nickname: String, score: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}