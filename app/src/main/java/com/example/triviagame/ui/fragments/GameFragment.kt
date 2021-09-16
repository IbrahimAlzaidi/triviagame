package com.example.triviagame.ui.fragments

import android.util.Log
import android.view.LayoutInflater
import com.example.triviagame.R
import com.example.triviagame.adapter.QuestionAdapter
import com.example.triviagame.data.RequestResult
import com.example.triviagame.databinding.GameFragmentBinding
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class GameFragment : BaseFragment<GameFragmentBinding>() {
    override val bindingInflater: (LayoutInflater) -> GameFragmentBinding
        get() = GameFragmentBinding :: inflate

    var counter = 0
    private val client = OkHttpClient()
    lateinit var question: String
    val resultFragment = ResultFragment()
    var answers = mutableListOf<String>()
    lateinit var correct:String


    override fun addCallBack() {
        binding?.submitButton?.setOnClickListener {
            if(counter == 9){
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container, resultFragment)
                    ?.addToBackStack(null)
                    ?.commit()
            }
            else{
            counter ++
            answers.clear()
            makeOkHTTPRequest(counter)
            }
        }
    }

    override fun setup() {
        makeOkHTTPRequest(counter)
    }
    fun makeOkHTTPRequest(index:Int) {
        val urlBuild = HttpUrl.Builder()
            .scheme("https")
            .host("opentdb.com")
            .addPathSegment("api.php")
            .addQueryParameter("amount","10")
            .addQueryParameter("category", "10")
            .addQueryParameter("difficulty","easy")
            .addQueryParameter("type","multiple")
            .build()

        val request = Request.Builder().url(urlBuild).build()
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.v("Fail","fail{${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    val result = Gson().fromJson(jsonString, RequestResult::class.java)

                    activity?.runOnUiThread {
                        val adapter = QuestionAdapter(answers)
                        binding?.answersRecycler?.adapter = adapter
                        binding?.questionBody?.text = question
                    }

                    correct = result.results[index].correct_answer
                    question = result.results.toMutableList()[index].question
                    result.results.toMutableList()[index].incorrect_answers.forEach{
                        answers.add(it)
                    }
                    answers.add(correct)
                    answers.shuffle()
                    Log.v("response", "correct:$correct - all:$answers")

                }
            }
        })
    }

}