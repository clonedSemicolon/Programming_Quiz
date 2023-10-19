package com.example.programming_quiz_app.data.repository

interface HomeScreenRepository {
    fun getString(key: String, defaultValue: String): String
    fun putString(key: String, value: String)
}