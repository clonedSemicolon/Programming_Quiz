package com.example.programming_quiz_app.ui.screens.quiz

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.programming_quiz_app.R
import com.example.programming_quiz_app.databinding.FragmentHomeBinding
import com.example.programming_quiz_app.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    companion object {
        fun newInstance() = QuizFragment()
    }

    private lateinit var viewModel: QuizViewModel
    private lateinit var binding: FragmentQuizBinding

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
           binding.number.text = it.size.toString();
       }
    }

}