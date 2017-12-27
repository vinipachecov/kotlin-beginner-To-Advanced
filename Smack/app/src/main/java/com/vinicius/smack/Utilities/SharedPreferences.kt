package com.vinicius.smack.Utilities

import android.content.Context
import com.android.volley.toolbox.Volley

/**
 * Created by vinicius on 12/27/17.
 */
class SharedPreferences(context: Context) {

    val PREFS_FILENAME = "prefs"
    val prefs = context.getSharedPreferences(PREFS_FILENAME, 0)

    val IS_LOGGED_IN = "isLogged"
    val AUTH_TOKEN = "authToken"
    val USER_EMAIL = "userEmail"

    var isLogged: Boolean
        get() = prefs.getBoolean(IS_LOGGED_IN, false)
        set(value) = prefs.edit().putBoolean(IS_LOGGED_IN, value).apply()

    var authToken: String
        get() = prefs.getString(AUTH_TOKEN, "")
        set(value) = prefs.edit().putString(AUTH_TOKEN, value).apply()
    var userEmail: String
        get() = prefs.getString(USER_EMAIL, "")
        set(value) = prefs.edit().putString(USER_EMAIL, value).apply()

    val requestQeue = Volley.newRequestQueue(context)
}