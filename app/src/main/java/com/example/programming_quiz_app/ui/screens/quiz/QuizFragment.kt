package com.example.programming_quiz_app.ui.screens.quiz


import Timer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.programming_quiz_app.R
import com.example.programming_quiz_app.data.model.Quiz
import com.example.programming_quiz_app.databinding.FragmentQuizBinding

import com.example.programming_quiz_app.ui.screens.home.HomeFragment
import com.example.programming_quiz_app.ui.screens.quiz.question.QuestionFragment
import com.example.programming_quiz_app.utils.Constants
import com.example.programming_quiz_app.utils.SharedPreference
import kotlin.math.max


class QuizFragment : Fragment() {

    companion object {
        fun newInstance() = QuizFragment()
    }

    private lateinit var viewModel: QuizViewModel
    private lateinit var binding: FragmentQuizBinding
    private lateinit var questionList:List<Quiz>
    private var currentQuestionId = 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[QuizViewModel::class.java]
        initialize()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    if (viewModel.currentQuestionIndex.value!! + 1 < questionList.size){
                        Toast.makeText(context,"Cant Go to home now",Toast.LENGTH_LONG).show()
                        return
                    }
                    loadHomeFragment()
                }
            }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback);
    }

    private fun initialize(){
       viewModel.showProgress.observe(viewLifecycleOwner) {
           binding.loader.isVisible = it;
       }

        viewModel.currentScore.observe(viewLifecycleOwner){
            "Score:${it}".also { binding.score.text = it }
        }

       viewModel.questionList.observe(viewLifecycleOwner){
            questionList = it
            binding.scoreDetail.visibility = View.VISIBLE
            viewModel.startAutoQuestionChange()
       }

       viewModel.currentQuestionIndex.observe(viewLifecycleOwner){

           // Question index and display in the heading section

           currentQuestionId = viewModel.currentQuestionIndex.value!! + 1
           "Questions : ${currentQuestionId}/${questionList.size}".also { binding.questionId.text = it }
           if(it<questionList.size){
               parentFragmentManager.beginTransaction().replace(
                   binding.questionView.id,
                   QuestionFragment.newInstance(
                       questionList[it],
                       onClickAction = ::onClickedAns
                   )
               ).commit()
           }
       }

       viewModel.isFinished.observe(viewLifecycleOwner){
           if(it){
               binding.questionView.visibility = View.INVISIBLE
               binding.scoreDetail.visibility = View.INVISIBLE
               binding.finishMsg.visibility = View.VISIBLE
               binding.llGoBack.visibility = View.VISIBLE

               SharedPreference.putString(
                   Constants.HIGH_SCORE_KEY,
                   max(viewModel.currentScore.value!!.toInt(),
                       SharedPreference.getString(Constants.HIGH_SCORE_KEY,Constants.DEFAULT_HIGH_SCORE).toInt()).toString()
               )

               context?.let {
                   Glide.with(requireContext())
                       .load(R.drawable.baseline_check_circle_outline_24)
                       .override(200,200)
                       .into(binding.checkMark)
               }
           }
       }

       binding.llGoBack.setOnClickListener(View.OnClickListener {
           loadHomeFragment()
       })
    }

    private fun onClickedAns(isCorrectAns:Boolean){
        if(isCorrectAns){
            // Updating the current score if answer is right.
            viewModel.updateScore(
                questionList[viewModel.currentQuestionIndex.value!!].score
            )
        }
        viewModel.forceLoadNextQuestion()
    }

    private fun loadHomeFragment(){

        /* Replacing the question Fragment with home fragment
        * OnBackPressed and GoToHome ButtonClick */

        parentFragmentManager.beginTransaction().replace(
            binding.rootLayout.id,
            HomeFragment.newInstance(
                highScore = SharedPreference.getString(Constants.HIGH_SCORE_KEY,Constants.DEFAULT_HIGH_SCORE)
            )
        ).commit()
    }

}