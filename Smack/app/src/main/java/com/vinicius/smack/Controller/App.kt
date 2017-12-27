package com.vinicius.smack.Controller

import android.app.Application
import com.vinicius.smack.Utilities.SharedPreferences

/**
 * Created by vinicius on 12/27/17.
 */
class App: Application() {

    companion object {
        lateinit var sharedPreferences: SharedPreferences
    }

    override fun onCreate() {
        sharedPreferences = SharedPreferences(applicationContext)
        super.onCreate()
        //initialize our shared preferences
    }
}