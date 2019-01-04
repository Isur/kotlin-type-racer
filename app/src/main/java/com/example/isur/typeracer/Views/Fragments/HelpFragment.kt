package com.example.isur.typeracer.Views.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.isur.typeracer.R

class HelpFragment : Fragment() {
    private var listenerHelp: OnHelpFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_help, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnHelpFragmentInteractionListener) {
            listenerHelp = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnMenuFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerHelp = null
    }


    interface OnHelpFragmentInteractionListener {
        fun onHelpFragmentInteraction()
    }

    companion object {

        @JvmStatic
        fun newInstance() = HelpFragment()
    }
}
