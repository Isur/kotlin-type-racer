package com.example.isur.typeracer.Model.Utils

import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.support.v7.app.AlertDialog
import com.example.isur.typeracer.R

object NoConnectionDialog {
    fun show(context: Context, action:()->Unit){
        val dialogBuilder = AlertDialog.Builder(context)

        dialogBuilder.run {
            setTitle(context.getString(R.string.noConnection))
            setMessage(context.getString(R.string.areYouConnected))
            setPositiveButton(context.getString(R.string.ok)) { _, _ ->
                action()
            }

        }
        val dialog = dialogBuilder.create()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
        dialog.getButton(Dialog.BUTTON_POSITIVE).isEnabled = false

        Handler().postDelayed({
            dialog.getButton(Dialog.BUTTON_POSITIVE).isEnabled = true

        }, 1000)
    }
}