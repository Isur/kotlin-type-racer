package com.example.isur.typeracer.Views.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.isur.typeracer.R

class AboutFragment : Fragment() {

    private var listenerAbout: OnAboutFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnAboutFragmentInteractionListener) {
            listenerAbout = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnMenuFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerAbout = null
    }

    interface OnAboutFragmentInteractionListener {
        fun onAboutFragmentInteraction()
    }

    companion object {

        @JvmStatic
        fun newInstance() = AboutFragment()
    }
}
