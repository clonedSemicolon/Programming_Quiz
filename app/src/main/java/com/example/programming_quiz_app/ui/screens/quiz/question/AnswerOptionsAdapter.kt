package com.example.programming_quiz_app.ui.screens.quiz.question

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.programming_quiz_app.R
import com.example.programming_quiz_app.databinding.ItemAnswerOptionBinding


class AnswerOptionAdapter(
    private val answerOptions: List<String>,
    private val correctAnswerIdx:Int,
    private val onItemClick: (String) -> Unit) : RecyclerView.Adapter<AnswerOptionAdapter.ViewHolder>() {

    private lateinit var binding: ItemAnswerOptionBinding
    private var selectedPosition = -1;

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val answerText: TextView = itemView.findViewById(R.id.answerText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemAnswerOptionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val answer = answerOptions[position]
        holder.answerText.text = answer
        val border = GradientDrawable()
        border.setColor(Color.WHITE);
        holder.itemView.setOnClickListener {
            if(selectedPosition != -1 && selectedPosition!=position){
                return@setOnClickListener
            }

            if(position == correctAnswerIdx){
                border.setStroke(10, Color.GREEN)
            }
            else{
                border.setStroke(10,Color.RED)
            }
            holder.itemView.background = border
            selectedPosition = position
            onItemClick(position.toString())

        }
    }

    override fun getItemCount(): Int {
        return answerOptions.size
    }


}
