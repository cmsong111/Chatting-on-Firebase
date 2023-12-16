package deu.ac.kr.csw.chatting.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import deu.ac.kr.csw.chatting.R

class LoadingDialog(
    private val context: Context
) {
    private val view: View? =
        LayoutInflater.from(context).inflate(R.layout.alert_dialog_progressbar, null)

    private val builder: AlertDialog.Builder = AlertDialog.Builder(context)

    private val dialog: AlertDialog = builder.setCancelable(false).setView(view).create()

    fun show() {
        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }
}