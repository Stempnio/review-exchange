package pl.edu.uj.reviewexchange.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.displayMessageToast(message : String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.displayMessageAlertDialog(message: String) {
    AlertDialog.Builder(context)
        .setMessage(message)
        .setNeutralButton("Ok") { _: DialogInterface, _: Int -> }
        .create()
        .show()
}