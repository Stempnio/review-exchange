package pl.edu.uj.reviewexchange.factory

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast

object MessageCreator {

    fun createMessage(message : String, displayType : DisplayType, context: Context){
        when(displayType) {
            DisplayType.ALERT_DIALOG -> MessageAlertDialog.displayMessage(message, context)
            DisplayType.TOAST -> MessageToast.displayMessage(message, context)
            else -> {}
        }
    }
}

enum class DisplayType {
    TOAST,
    ALERT_DIALOG,
    SNACKBAR
}

interface MessageInterface {
    fun displayMessage(message: String, context: Context)
}

object MessageAlertDialog : MessageInterface {

    override fun displayMessage(message : String, context : Context) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .setNeutralButton("Ok") { _: DialogInterface, _: Int -> }
            .create()
            .show()
    }
}

object MessageToast : MessageInterface {

    override fun displayMessage(message : String, context : Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

