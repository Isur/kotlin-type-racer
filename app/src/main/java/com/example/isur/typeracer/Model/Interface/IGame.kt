package com.example.isur.typeracer.Model.Interface

import android.os.Handler
import com.example.isur.typeracer.Model.GameInteractor

interface IGame : Runnable {
    val words: MutableList<String>
    var currentWord: String
    var typingWord: String
    var points: Int
    var time: Int
    var isFinished: Boolean
    val handler: Handler
    var timerRunning: Boolean
    val gameInteractor: GameInteractor
    fun startGame()
    fun stopGame()
    fun compareWords(): Boolean
    fun incrementPoints()
    fun getWords()
    fun restart(gameTime: Int)
}