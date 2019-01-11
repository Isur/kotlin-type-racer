package com.example.isur.typeracer.Views.Interface

import android.support.v4.app.Fragment

interface IMainActivity {
    fun changeFragment(fragment: Fragment)
    fun destroyFragment(fragment: Fragment)
    fun exitGame()
}