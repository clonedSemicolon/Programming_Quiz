package com.example.programming_quiz_app.ui.screens.quiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.programming_quiz_app.data.model.Quiz
import com.example.programming_quiz_app.data.repository.QuizRepository
import com.example.programming_quiz_app.domain.usecase.QuizDataUseCase
import com.example.programming_quiz_app.utils.ioThenMain
import java.lang.Exception

class QuizViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var quizRepository: QuizRepository
    private lateinit var quizDataUseCase: QuizDataUseCase

    val showProgress:MutableLiveData<Boolean> = MutableLiveData()
    val isError:MutableLiveData<Boolean> = MutableLiveData()
    val questionList:MutableLiveData<List<Quiz>> = MutableLiveData()

    init {
        quizRepository = QuizRepository(application.applicationContext)
        quizDataUseCase = QuizDataUseCase(quizRepository)
        fetchQuestions<Quiz>();
    }

    private fun <T> fetchQuestions(){
        try {
            showProgress.postValue(true);
            ioThenMain({
                quizDataUseCase.execute()
            },{
                questionList.postValue(it)
                showProgress.postValue(false)
            })

        }catch (ex:Exception){
            isError.postValue(true)
        }
    }
}