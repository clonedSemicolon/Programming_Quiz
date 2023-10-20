package com.example.programming_quiz_app.data.model

data class Quiz(
    val question: String,
    val answers: Map<String, String>,
    val questionImageUrl: String?,
    val correctAnswer: String,
    val score: Int
)


