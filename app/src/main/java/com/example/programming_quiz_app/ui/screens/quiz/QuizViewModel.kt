package com.example.programming_quiz_app.ui.screens.quiz

import android.app.Application
import android.os.Handler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.programming_quiz_app.data.model.Quiz
import com.example.programming_quiz_app.data.repository.QuizRepository
import com.example.programming_quiz_app.domain.usecase.QuizDataUseCase
import com.example.programming_quiz_app.utils.ioThenMain

class QuizViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var quizRepository: QuizRepository
    private lateinit var quizDataUseCase: QuizDataUseCase

    val showProgress:MutableLiveData<Boolean> = MutableLiveData()
    val isError:MutableLiveData<Boolean> = MutableLiveData()
    val isFinished:MutableLiveData<Boolean> = MutableLiveData(false)
    val questionList:MutableLiveData<List<Quiz>> = MutableLiveData()
    val currentQuestionIndex:MutableLiveData<Int> = MutableLiveData()
    private val questionChangeHandler = Handler()

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
                currentQuestionIndex.postValue(0)
                showProgress.postValue(false)
            })

        }catch (ex:Exception){
            isError.postValue(true)
        }
    }

    fun startAutoQuestionChange() {
        questionChangeHandler.postDelayed(questionChangeRunnable, 2000) // 10 seconds
    }

    private val questionChangeRunnable = Runnable {
        currentQuestionIndex.value = currentQuestionIndex.value?.plus(1)
        if(currentQuestionIndex.value!! < (questionList.value?.size!!)){
            currentQuestionIndex.postValue(currentQuestionIndex.value)
            startAutoQuestionChange()
        }
        else{
            isFinished.postValue(true)
        }
    }
}