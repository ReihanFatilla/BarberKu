package com.example.BookBath.data.sharedpref

import android.content.Context
import android.content.SharedPreferences

class LoginPreference(context: Context) {
    private val PREF_NAME = "loginpreferences"
    var sharedPreferences: SharedPreferences
    var prefEditor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefEditor = sharedPreferences.edit()
    }

    fun put(key: String, value: Boolean){
        prefEditor.putBoolean(key, value)
            .apply()
    }

    fun getLoginStatus(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun logout(){
        prefEditor.clear()
            .apply()
    }

    companion object{
        const val PREF_LOGIN_STATUS = "PREF_USERNAME"
    }
}