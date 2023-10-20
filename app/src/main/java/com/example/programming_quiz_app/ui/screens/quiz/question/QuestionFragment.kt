package com.example.programming_quiz_app.ui.screens.quiz.question

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.programming_quiz_app.R
import com.example.programming_quiz_app.data.model.Quiz
import com.example.programming_quiz_app.databinding.FragmentQuestionBinding
import com.example.programming_quiz_app.databinding.FragmentQuizBinding
import java.lang.Exception

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
        binding.questionText.text = quiz.question

        quiz.questionImageUrl?.let {url->
            binding.questionImage.visibility = View.VISIBLE
            context?.let {
                Glide.with(it).load(
                    url)

                    .into(
                    binding.questionImage
                )
            }
        }
        val answerOptionAdapter = AnswerOptionAdapter(
            quiz.answers.values.toList()
        ) { selectedAns ->

        }
        binding.answersRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.answersRecyclerView.adapter = answerOptionAdapter
    }


}