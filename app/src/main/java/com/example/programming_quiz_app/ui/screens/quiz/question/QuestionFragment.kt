package com.example.programming_quiz_app.ui.screens.quiz.question

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.programming_quiz_app.R
import com.example.programming_quiz_app.data.model.Quiz
import com.example.programming_quiz_app.databinding.FragmentQuestionBinding
import com.example.programming_quiz_app.databinding.FragmentQuizBinding

class QuestionFragment : Fragment() {
    private lateinit var quiz:Quiz;
    companion object {
        fun newInstance(
            quiz: Quiz
        ) = QuestionFragment().apply {
            this.quiz = quiz
        }
    }

    private lateinit var viewModel: QuestionViewModel
    private lateinit var binding:FragmentQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)
        intialize()
    }

    private fun intialize() {
        binding.question.text = quiz.question
    }


}