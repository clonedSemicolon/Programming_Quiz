package com.example.programming_quiz_app.ui.screens.quiz.question

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.service.autofill.OnClickAction
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
    private lateinit var onClickAction:(isCorrect:Boolean)->Unit

    companion object {
        fun newInstance(
            quiz: Quiz,
            onClickAction: (isCorrect: Boolean)->Unit
        ) = QuestionFragment().apply {
            this.quiz = quiz
            this.onClickAction = onClickAction
        }
    }

    private lateinit var viewModel: QuestionViewModel
    private lateinit var binding:FragmentQuestionBinding
    val answersInAlphabeticalOrder = listOf("A", "B", "C", "D", "E")

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
        "Score:${quiz.score}".also { binding.questionScore.text = it }
        quiz.questionImageUrl?.let {url->
            binding.questionImage.visibility = View.VISIBLE
            context?.let {
                Glide.with(it).load(url)
                    .error(R.drawable.error_icon)
                    .into(binding.questionImage)

            }
        }
        val answerOptionAdapter = AnswerOptionAdapter(
            quiz.answers.values.toList(),
            answersInAlphabeticalOrder.indexOf(quiz.correctAnswer)
        ) {
            val rightAnsIndex = answersInAlphabeticalOrder.indexOf(quiz.correctAnswer)

            // If the given answer is wrong then the
            // correct answer should be green bordered.

            val rightAnswerView = binding.answersRecyclerView.findViewHolderForAdapterPosition(rightAnsIndex)
            val border = GradientDrawable()
            border.setColor(Color.WHITE);
            border.setStroke(10, Color.GREEN)
            rightAnswerView?.itemView?.background = border

            // Checking if the answer is correct.
            // Execute the action using lamda function passed by parameters.

            onClickAction(
                 it.toInt() == rightAnsIndex
            )
        }
        binding.answersRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.answersRecyclerView.adapter = answerOptionAdapter
    }


}