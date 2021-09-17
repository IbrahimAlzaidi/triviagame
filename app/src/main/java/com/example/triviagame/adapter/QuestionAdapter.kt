package com.example.triviagame.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.triviagame.R
import com.example.triviagame.data.ResultType
import com.example.triviagame.databinding.RecyclerCardBinding

class QuestionAdapter(val context:Context,private val questionsList: List<String>, private val correct:String ) : RecyclerView
    .Adapter<QuestionAdapter.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.
        from(parent.context).inflate(R.layout.recycler_card, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.binding.Qestion.setOnClickListener {
            var result = 0
            val chosen = questionsList[position]
            Log.v("chosenText",chosen)
            if (chosen == correct) {

              Toast.makeText(context,"Correct",Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(context, "Incorrect", Toast.LENGTH_SHORT).show()
            }
        }

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