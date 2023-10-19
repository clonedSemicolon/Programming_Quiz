package com.example.programming_quiz_app.data.source

import android.content.Context
import android.preference.PreferenceManager
import com.example.programming_quiz_app.data.repository.HomeScreenRepository

class HomeScreenDataSource(private val context: Context):HomeScreenRepository {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key,defaultValue)?:defaultValue
    }

    override fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key,value)
    }
}