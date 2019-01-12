package com.example.isur.typeracer.Model

import android.os.Handler
import android.util.Log
import com.example.isur.typeracer.Model.Interface.IGame
import com.example.isur.typeracer.Views.Interface.IGameBoard
import kotlin.properties.Delegates
import kotlin.random.Random

class Game(override var time: Int, context: IGameBoard) : IGame {
    private var listenerGame: GameListener = context
    override var words: MutableList<String> = mutableListOf()
    override var points: Int = 0
    override var timerRunning = false
    override val handler = Handler()
    override val gameInteractor = GameInteractor()
    // OBSERVABLE:
    override var currentWord: String by Delegates.observable("") { _, _, _ ->
        listenerGame.listenerSetNewWord(currentWord)
    }
    override var typingWord: String by Delegates.observable("") { _, _, _ ->
        if (compareWords()) {
            incrementPoints()
            popWord(currentWord)
            changeCurrentWord()
            listenerGame.listenerClearInput()
        }
    }
    private var timeText: String by Delegates.observable(time.toString()) { _, _, newValue ->
        listenerGame.listenerSetTime(newValue)
    }

    init {
        getWords()
        changeCurrentWord()
    }

    override fun startGame() {
        timerRunning = true
        run()
    }

    override fun stopGame() {
        timerRunning = false
        listenerGame.listenerStopGame()
    }

    override fun compareWords(): Boolean {
        return typingWord.toLowerCase() == currentWord.toLowerCase()
    }

    override fun incrementPoints() {
        points++
    }

    override fun getWords() {
        words.addAll(gameInteractor.getWords())
    }

    private fun popWord(word: String) {
        words.remove(word)
        if (words.size < 3) {
            getWords()
        }
    }

    private fun changeCurrentWord(){
        currentWord = words[0]
    }

    override fun run() {
        if (timerRunning) {
            decrementTime()
            Log.i("GAME", "Game is running with time: $time")
            handler.postDelayed(this, 1000)
        }
    }

    private fun decrementTime() {
        time--
        timeText = time.toString()
        if (time == 0) {
            timerRunning = false
            stopGame()
        }
    }

    interface GameListener {
        fun listenerSetTime(time: String)
        fun listenerStopGame()
        fun listenerSetNewWord(word: String)
        fun listenerClearInput()
    }

}