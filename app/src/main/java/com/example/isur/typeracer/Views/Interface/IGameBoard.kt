package com.example.isur.typeracer.Views.Interface

import android.widget.TextView

interface IGameBoard {
    var wordInput: TextView
    fun showNextWord()
    fun showSubmitDialog()
}