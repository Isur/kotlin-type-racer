package com.example.isur.typeracer.Views.Fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import com.example.isur.typeracer.Model.Game
import com.example.isur.typeracer.Model.GameInteractor
import com.example.isur.typeracer.Presenters.GamePresenter

import com.example.isur.typeracer.R
import com.example.isur.typeracer.Views.Interface.IGameBoard
import kotlinx.android.synthetic.main.custom_dialog.view.*
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_game.view.*

class GameFragment : Fragment(), IGameBoard {
    private var listenerGame: OnGameFragmentInteractionListener? = null
    lateinit var presenter: GamePresenter
    override lateinit var wordTextView: TextView
    override lateinit var wordInput: EditText
    override lateinit var timer: TextView
    override lateinit var game: Game

    override fun listenerSetTime(time: String) {
        timer.text = time
    }

    override fun listenerStopGame() {
        showSubmitDialog()
    }

    override fun listenerSetNewWord(word: String) {
        wordTextView.text = word
    }

    override fun listenerClearInput() {
        wordInput.text.clear()
    }

    private fun init() {
        wordInput.requestFocus()
        val gameTime = 20
        if (!::game.isInitialized) {
            presenter.getGame(gameTime)
        }
        timer.text = gameTime.toString()
        wordInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(!game.timerRunning) {
                    game.startGame()
                }
                game.typingWord = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        wordTextView = view.wordTextView
        wordInput = view.wordInput
        timer = view.timeTextView
        presenter = GamePresenter(this, GameInteractor())
        init()
        return view
    }

    override fun showSubmitDialog() {
        val dialogBuilder = AlertDialog.Builder(this.view!!.context)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_dialog, null)
        dialogBuilder.setView(dialogView)

        val editText = dialogView.editTextName

        dialogBuilder.run {
            // TODO("strings to -> res/values/strings")
            setTitle("Your Score: ${game.points} ")
            setMessage("Enter your nickname if you want to submit your score:")
            setPositiveButton("Submit") { _, _ ->
                presenter.postScore(editText.text.toString(), game.points)
            }
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
        }
        val dialog = dialogBuilder.create()
        dialog.show()
        dialog.getButton(Dialog.BUTTON_POSITIVE).isEnabled = false
        dialog.getButton(Dialog.BUTTON_NEGATIVE).isEnabled = false
        Handler().postDelayed({
            dialog.getButton(Dialog.BUTTON_POSITIVE).isEnabled = true
            dialog.getButton(Dialog.BUTTON_NEGATIVE).isEnabled = true
        }, 1000)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnGameFragmentInteractionListener) {
            listenerGame = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnMenuFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerGame = null
    }

    interface OnGameFragmentInteractionListener {
        fun onGameFragmentInteraction()
    }

    companion object {
        @JvmStatic
        fun newInstance() = GameFragment()
    }
}
