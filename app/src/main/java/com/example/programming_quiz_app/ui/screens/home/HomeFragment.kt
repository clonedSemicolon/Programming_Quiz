package com.example.programming_quiz_app.ui.screens.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.programming_quiz_app.R
import com.example.programming_quiz_app.databinding.FragmentHomeBinding
import com.example.programming_quiz_app.ui.screens.quiz.QuizFragment
import com.example.programming_quiz_app.utils.Constants

class HomeFragment : Fragment() {

    companion object {
        fun newInstance(
            highScore:String
        ) = HomeFragment().apply {
            arguments = Bundle().apply {
                putCharSequence(
                    Constants.BUNDLE_HIGH_SCORE_KEY,highScore
                )
            }
        }
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var highScore: String
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.scorePoint.text = getString(R.string.points, highScore)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.startBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, QuizFragment.newInstance(
                    ))
                .commit();
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        highScore = arguments?.getString(Constants.BUNDLE_HIGH_SCORE_KEY).toString()
    }

}