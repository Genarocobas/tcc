package inatel.br.nfccontrol.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {

    companion object {
        val PREFS_FILENAME = "br.inatel.tcc"
        val CAN_REGISTER = "can_register"
    }

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var canRegister: Boolean = false
        get() = prefs.getBoolean(CAN_REGISTER, field)
        set(value) = prefs.edit().putBoolean(CAN_REGISTER, value).apply()

}