package com.example.programming_quiz_app.data.source

import com.example.programming_quiz_app.data.model.Quiz

interface QuizDataSource {
    fun getQuizQuestions():List<Quiz>
}