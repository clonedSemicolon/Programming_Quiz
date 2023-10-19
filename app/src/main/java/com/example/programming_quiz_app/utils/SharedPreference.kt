package com.example.programming_quiz_app.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreference {

    private lateinit var sharedPreferences: SharedPreferences
    private const val PREFS_NAME = "PhSharedPreference"

    fun initialize(context: Context){
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }
}