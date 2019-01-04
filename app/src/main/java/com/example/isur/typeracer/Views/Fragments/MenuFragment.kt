package com.example.isur.typeracer.Views.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.isur.typeracer.R
import com.example.isur.typeracer.Views.VIEWS
import kotlinx.android.synthetic.main.fragment_menu.view.*

class MenuFragment : Fragment() {
    private var listenerMenu: OnMenuFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        view.playButton.setOnClickListener {
            listenerMenu?.onMenuFragmentInteraction(VIEWS.GAME)
        }
        view.scoreButton.setOnClickListener {
            listenerMenu?.onMenuFragmentInteraction(VIEWS.SCORE)
        }
        view.aboutButton.setOnClickListener {
            listenerMenu?.onMenuFragmentInteraction(VIEWS.ABOUT)
        }
        view.helpButton.setOnClickListener {
            listenerMenu?.onMenuFragmentInteraction(VIEWS.HELP)
        }
        view.exitButton.setOnClickListener {
            listenerMenu?.onMenuFragmentInteraction(VIEWS.EXIT)
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMenuFragmentInteractionListener) {
            listenerMenu = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnMenuFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerMenu = null
    }

    interface OnMenuFragmentInteractionListener {
        fun onMenuFragmentInteraction(s: VIEWS)
    }

    companion object {

        @JvmStatic
        fun newInstance() = MenuFragment()
    }
}
