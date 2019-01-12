package com.example.isur.typeracer.Model.Utils

import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.support.v7.app.AlertDialog

object NoConnectionDialog {
    fun showNoConnectionDialog(context: Context,action:()->Unit){
        val dialogBuilder = AlertDialog.Builder(context)
//TODO change to values strings
        dialogBuilder.run {
            setTitle("No connection")
            setMessage("Make sure you are connected to the internet")
            setPositiveButton("OK") { _, _ ->
                action()
            }

        }
        val dialog = dialogBuilder.create()
        dialog.show()
        dialog.getButton(Dialog.BUTTON_POSITIVE).isEnabled = false

        Handler().postDelayed({
            dialog.getButton(Dialog.BUTTON_POSITIVE).isEnabled = true

        }, 1000)
    }
}