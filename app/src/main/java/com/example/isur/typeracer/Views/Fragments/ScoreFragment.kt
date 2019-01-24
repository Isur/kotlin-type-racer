package com.example.isur.typeracer.Views.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.isur.typeracer.Model.DataModels.ScoreList
import com.example.isur.typeracer.Model.ScoreInteractor
import com.example.isur.typeracer.Model.Utils.Network.ConnectionInfo
import com.example.isur.typeracer.Model.Utils.Network.NoConnectivityException
import com.example.isur.typeracer.Presenters.ScorePresenter
import com.example.isur.typeracer.R

import com.example.isur.typeracer.Views.Interface.IScoreBoard

class ScoreFragment : Fragment(), IScoreBoard {

    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_score_list, container, false)
        val presenter = ScorePresenter(this, ScoreInteractor())
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                try {

                adapter = ScoreRecyclerViewAdapter(presenter.getScores(7), listener)
                }catch (ex:NoConnectivityException){
                    ConnectionInfo.sendNoConnection(context!!.applicationContext)
                }
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: ScoreList.Score?)
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            ScoreFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
