package com.example.isur.typeracer.Presenters

import com.example.isur.typeracer.Model.Interface.IGameInteractor
import com.example.isur.typeracer.Views.Interface.IGameBoard

class GamePresenter(val view: IGameBoard, val interactor: IGameInteractor){
//    fun getWord() {
//        view.wordTextView.text = interactor.getWord()
//    }
    fun getWords(): Array<String>{
        return interactor.getWords()
    }

    fun postScore(nickname: String, score: Int) {
        interactor.postScore(nickname, score)
    }

    fun getGame(time: Int) {
        view.game = interactor.getGame(time, view)
    }
}