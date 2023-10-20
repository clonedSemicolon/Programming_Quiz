package com.example.programming_quiz_app.domain.usecase

import com.example.programming_quiz_app.data.model.Quiz
import com.example.programming_quiz_app.data.repository.QuizRepository

class QuizDataUseCase(private val repository: QuizRepository) {
    suspend fun execute():List<Quiz>{
        return repository.getQuizQuestions()
    }
}