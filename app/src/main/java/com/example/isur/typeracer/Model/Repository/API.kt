package com.example.isur.typeracer.Model.Repository

import com.example.isur.typeracer.Model.DataModels.ScoreList
import com.example.isur.typeracer.Model.Interface.IAPI

object API: IAPI {

    override fun getWord():String {
        TODO("not implemented")
    }

    override fun getWords():Array<String> {
        TODO("not implemented")
    }

    override fun getScores():ScoreList {
        TODO("not implemented")
    }

    override fun postScore(nickname: String, score: Int) {
        TODO("not implemented")
    }

}