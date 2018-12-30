package com.example.isur.typeracer.Model

import com.example.isur.typeracer.Model.DataModels.ScoreList
import com.example.isur.typeracer.Model.Interface.IScoreInteractor
import com.example.isur.typeracer.Model.Repository.MockAPI


class ScoreInteractor:IScoreInteractor{
    val API = MockAPI
    override fun getScores():ScoreList {
        return API.getScores()
    }

}