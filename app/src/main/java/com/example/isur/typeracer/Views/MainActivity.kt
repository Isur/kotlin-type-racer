package com.example.isur.typeracer.Views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.isur.typeracer.Model.DataModels.ScoreList
import com.example.isur.typeracer.R
import com.example.isur.typeracer.Views.Fragments.*
import com.example.isur.typeracer.Views.Interface.IMainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), IMainActivity,
    GameFragment.OnGameFragmentInteractionListener,
    ScoreFragment.OnListFragmentInteractionListener,
    HelpFragment.OnHelpFragmentInteractionListener,
    AboutFragment.OnAboutFragmentInteractionListener,
    MenuFragment.OnMenuFragmentInteractionListener {

    private lateinit var gameFragment: GameFragment
    private lateinit var scoreFragment: ScoreFragment
    private lateinit var helpFragment: HelpFragment
    private lateinit var aboutFragment: AboutFragment
    private lateinit var menuFragment: MenuFragment

    override fun onHelpFragmentInteraction() {}

    override fun onAboutFragmentInteraction() {}

    override fun onGameFragmentInteraction(s: VIEWS) {
        destroyFragment(gameFragment)
        when(s){
            VIEWS.MENU -> changeFragment(menuFragment)

            VIEWS.SCORE -> changeFragment(scoreFragment)
        }
    }

    override fun onListFragmentInteraction(item: ScoreList.Score?) {}

    override fun onMenuFragmentInteraction(s: VIEWS) {
        when (s) {
            VIEWS.MENU -> changeFragment(menuFragment)
            VIEWS.GAME -> changeFragment(gameFragment)
            VIEWS.SCORE -> changeFragment(scoreFragment)
            VIEWS.HELP -> changeFragment(helpFragment)
            VIEWS.ABOUT -> changeFragment(aboutFragment)
            VIEWS.EXIT -> exitGame()
        }
    }

    override fun exitGame() {
        exitProcess(-1)
    }

    override fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(container.id, fragment)
            .addToBackStack(fragment.toString())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    override fun destroyFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .remove(fragment)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_back -> {
                changeFragment(menuFragment)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun askForPermission() {
        TODO("Not implemented - ask for permission INTERNET")
    }

    override fun checkPermission(): Boolean {
        TODO("Not implemented - check for permission INTERNET")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        TODO("Uncomment when implemented")
//        if(!checkPermission()){
//           askForPermission()
//        }
        setContentView(R.layout.activity_main)

//        if(checkPermission()){
        gameFragment = GameFragment.newInstance()
        scoreFragment = ScoreFragment.newInstance(1)
        helpFragment = HelpFragment.newInstance()
        aboutFragment = AboutFragment.newInstance()
        menuFragment = MenuFragment.newInstance()
        changeFragment(menuFragment)
//        } else {
//        // TODO("If there is no permission granted -> Show info about permission required.")
//        }
    }

}
