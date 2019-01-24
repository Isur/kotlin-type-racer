package com.example.isur.typeracer.Views

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.isur.typeracer.Model.DataModels.ScoreList
import com.example.isur.typeracer.Model.Utils.Network.ConnectionInfo
import com.example.isur.typeracer.Model.Utils.NoConnectionDialog
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
    private lateinit var connectionReceiver: BroadcastReceiver

    override fun onHelpFragmentInteraction() {}

    override fun onAboutFragmentInteraction() {}

    override fun onGameFragmentInteraction(s: VIEWS) {
        when (s) {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadingBar.visibility = View.VISIBLE
        txt_loading.visibility = View.VISIBLE
        gameFragment = GameFragment.newInstance()
        scoreFragment = ScoreFragment.newInstance(1)
        helpFragment = HelpFragment.newInstance()
        aboutFragment = AboutFragment.newInstance()
        menuFragment = MenuFragment.newInstance()
        val serverOK = ConnectionInfo.checkServerStatus()
        if (serverOK) {
            changeFragment(menuFragment)
            loadingBar.visibility = View.INVISIBLE

            txt_loading.visibility = View.INVISIBLE
        } else NoConnectionDialog.show(this@MainActivity) {
            //goToWifiSettings()
            exitGame() }
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter(ConnectionInfo.CONNECTION_ACTION)
        connectionReceiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {
                loadingBar.visibility = View.VISIBLE
                txt_loading.visibility = View.VISIBLE
                NoConnectionDialog.show(this@MainActivity) {
                     //restartActivity()
                    //exitGame()
                    goToWifiSettings()}
                loadingBar.visibility = View.INVISIBLE

                txt_loading.visibility = View.INVISIBLE
            }
        }
        registerReceiver(connectionReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(connectionReceiver)
    }

    private fun restartActivity() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }
    private fun goToWifiSettings(){
        val i = Intent( Settings.ACTION_WIFI_SETTINGS)
        startActivity(i)
        finish()
    }


}
