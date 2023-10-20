package com.example.programming_quiz_app.data.repository

import android.content.Context
import android.util.Log
import com.example.programming_quiz_app.data.model.Quiz
import com.example.programming_quiz_app.data.model.QuizList
import com.example.programming_quiz_app.data.source.QuizDataSource
import com.example.programming_quiz_app.utils.readFile
import com.google.gson.Gson
import java.lang.Exception

class QuizRepository(private val context: Context) : QuizDataSource {


    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.readFile(fileName)
        } catch (exception: Exception) {
            return null
        }
        return jsonString
    }

    override fun getQuizQuestions(): List<Quiz> {
        val jsonFileString = getJsonDataFromAsset(context, "data.json")
        val quizList = Gson().fromJson(jsonFileString, QuizList::class.java).questions
        return quizList
    }
}