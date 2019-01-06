package com.example.isur.typeracer.Model.Interface

import android.os.Handler

interface IGame : Runnable {
    val words: MutableList<String>
    var currentWord: String
    var typingWord: String
    var points: Int
    var time: Int
    val handler: Handler
    var timerRunning: Boolean
    fun startGame()
    fun stopGame()
    fun compareWords(): Boolean
    fun incrementPoints()
    fun getWords()
}