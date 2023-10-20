package com.example.programming_quiz_app.ui.screens.quiz

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.programming_quiz_app.R
import com.example.programming_quiz_app.data.model.Quiz
import com.example.programming_quiz_app.databinding.FragmentQuizBinding
import com.example.programming_quiz_app.ui.screens.quiz.question.QuestionFragment

class QuizFragment : Fragment() {

    companion object {
        fun newInstance() = QuizFragment()
    }

    private lateinit var viewModel: QuizViewModel
    private lateinit var binding: FragmentQuizBinding
    private lateinit var questionList:List<Quiz>

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

    private fun initialize(){
       viewModel.showProgress.observe(viewLifecycleOwner) {
           binding.loader.isVisible = it;
       }

       viewModel.questionList.observe(viewLifecycleOwner){
            questionList = it
            binding.scoreDetail.visibility = View.VISIBLE
            viewModel.startAutoQuestionChange()
       }

       viewModel.currentQuestionIndex.observe(viewLifecycleOwner){
           binding.scoreDetail.visibility = View.VISIBLE
           if(it<questionList.size){
               parentFragmentManager.beginTransaction().replace(
                   binding.questionView.id,
                   QuestionFragment.newInstance(
                       questionList[it]
                   )
               ).commit()
           }
       }

       viewModel.isFinished.observe(viewLifecycleOwner){
           if(it){
               binding.questionView.visibility = View.INVISIBLE
               binding.scoreDetail.visibility = View.INVISIBLE
               binding.finishTxt.text = getString(R.string.finished_successfully)
           }
       }
    }

}