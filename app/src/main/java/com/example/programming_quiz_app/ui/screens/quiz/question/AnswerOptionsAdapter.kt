package com.example.programming_quiz_app.ui.screens.quiz.question

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.programming_quiz_app.R

class AnswerOptionAdapter(private val answerOptions: List<String>, private val onItemClick: (String) -> Unit) : RecyclerView.Adapter<AnswerOptionAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val answerText: TextView = itemView.findViewById(R.id.answerText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_answer_option, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val answer = answerOptions[position]
        holder.answerText.text = answer
        holder.itemView.setOnClickListener {
            onItemClick(answer)
        }
    }

    override fun getItemCount(): Int {
        return answerOptions.size
    }
}
