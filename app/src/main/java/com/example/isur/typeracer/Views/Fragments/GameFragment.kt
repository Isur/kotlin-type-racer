package com.example.isur.typeracer.Views.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.isur.typeracer.Model.GameInteractor
import com.example.isur.typeracer.Presenters.GamePresenter

import com.example.isur.typeracer.R
import com.example.isur.typeracer.Views.Interface.IGameBoard
import kotlinx.android.synthetic.main.custom_dialog.view.*
import kotlinx.android.synthetic.main.fragment_game.view.*

class GameFragment : Fragment(), IGameBoard {
    private var listenerGame: OnGameFragmentInteractionListener? = null
    lateinit var presenter: GamePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        presenter = GamePresenter(this, GameInteractor())
        val word = presenter.getWord()
        view.wordTextView.text = word
        view.wordInput.requestFocus()

        view.tempButton.setOnClickListener {
            showNewNameDialog()
        }

        return view
    }

    private fun showNewNameDialog() {
        val dialogBuilder = AlertDialog.Builder(this.view!!.context)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_dialog, null)
        dialogBuilder.setView(dialogView)

        val editText = dialogView.editTextName

        dialogBuilder.run {
            setTitle("Your Score: ")
            setMessage("Enter your nickname if you want to submit your score:")
            setPositiveButton("Submit") { dialog, whichButton ->
                presenter.postScore(editText.text.toString(), 10)
            }
            setNegativeButton("Cancel") { dialog, whichButton ->
                dialog.cancel()
            }
        }
        val b = dialogBuilder.create()
        b.show()
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
        fun newInstance() =
            GameFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
