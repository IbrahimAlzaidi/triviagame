package com.example.triviagame.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.triviagame.R
import com.example.triviagame.data.ResultType
import com.example.triviagame.databinding.RecyclerCardBinding

class QuestionAdapter(val questionsList: List<String>) : RecyclerView
    .Adapter<QuestionAdapter.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.
        from(parent.context).inflate(R.layout.recycler_card, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val currentQuestion = questionsList[position]
        holder.binding.apply {
            Qestion.text = currentQuestion
        }
    }

    override fun getItemCount() = questionsList.size

    class QuestionViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem){
        val binding = RecyclerCardBinding.bind(viewItem)
    }
}