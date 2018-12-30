package com.example.isur.typeracer.Presenters

import com.example.isur.typeracer.Model.Interface.IGameInteractor
import com.example.isur.typeracer.Views.Interface.IGameBoard

class GamePresenter(val view: IGameBoard, val interactor: IGameInteractor){
    fun getWord():String{
        return interactor.getWord()
    }
    fun getWords(): Array<String>{
        return interactor.getWords()
    }
}