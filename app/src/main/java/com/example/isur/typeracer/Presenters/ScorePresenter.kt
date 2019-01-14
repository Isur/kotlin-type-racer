package com.example.isur.typeracer.Presenters

import com.example.isur.typeracer.Model.DataModels.ScoreList
import com.example.isur.typeracer.Model.Interface.IScoreInteractor
import com.example.isur.typeracer.Views.Interface.IScoreBoard

class ScorePresenter(val view: IScoreBoard, val interactor: IScoreInteractor) {
    fun getScores(limit: Int): List<ScoreList.Score> {
        return interactor.getScores(limit)
    }
}