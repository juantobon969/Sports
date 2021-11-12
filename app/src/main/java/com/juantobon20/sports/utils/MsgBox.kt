package com.juantobon20.sports.utils

import android.app.AlertDialog
import android.content.Context
import com.juantobon20.sports.R

fun alertDialog(
    context: Context?,
    listener: IAlertDialogListener?,
    msg: String,
    title: String = "",
    opCancel: Boolean = false,
    btnPositive: Int = R.string.aceptar,
    btnNegative: Int = R.string.cancelar,
    isCancelable: Boolean = false
) {
    if (context == null) return
    val alertDialogBuilder = AlertDialog.Builder(context)
    alertDialogBuilder.setTitle(title)
    alertDialogBuilder.setMessage(msg)
    alertDialogBuilder.setCancelable(isCancelable)
    alertDialogBuilder.setPositiveButton(context.getText(btnPositive)) { _, _ -> listener?.btnPositive() }
    if (opCancel) alertDialogBuilder.setNegativeButton(context.getText(btnNegative)) { _, _ -> listener?.btnNegative() }
    alertDialogBuilder.show()
}

interface IAlertDialogListener {
    fun btnPositive()
    fun btnNegative()
}