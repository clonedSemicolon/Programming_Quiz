package com.example.programming_quiz_app.ui.screens

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.programming_quiz_app.R
import com.example.programming_quiz_app.ui.screens.home.HomeFragment
import com.example.programming_quiz_app.utils.Constants
import com.example.programming_quiz_app.utils.SharedPreference


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SharedPreference.initialize(applicationContext)

        val highScore = SharedPreference.getString(
            Constants.HIGH_SCORE_KEY,Constants.DEFAULT_HIGH_SCORE
        ).toString()


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment.newInstance(
                    highScore))
                .commit();
        }
    }
}