package com.example.isur.typeracer.Views.Interface

import android.widget.EditText
import android.widget.TextView
import com.example.isur.typeracer.Model.Game

interface IGameBoard : Game.GameListener {
    var wordTextView: TextView
    var wordInput: EditText
    var timer: TextView
    var game: Game
    fun showNextWord()
    fun showSubmitDialog()
}