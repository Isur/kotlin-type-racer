package com.example.isur.typeracer.Views.Fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.isur.typeracer.Model.DataModels.ScoreList
import com.example.isur.typeracer.R


import com.example.isur.typeracer.Views.Fragments.ScoreFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_score.view.*

class ScoreRecyclerViewAdapter(
    private val mValues: List<ScoreList.Score>,
    private val mListener: OnListFragmentInteractionListener?
    ) : RecyclerView.Adapter<ScoreRecyclerViewAdapter.ViewHolder>() {

        private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as ScoreList.Score
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_score, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = (position + 1).toString()
        holder.mContentView.text = item.nickname
        holder.mScoreView.text = item.score.toString()

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content
        val mScoreView: TextView = mView.score
    }
}
