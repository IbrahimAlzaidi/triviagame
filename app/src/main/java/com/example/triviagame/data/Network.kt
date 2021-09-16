package com.example.triviagame.data

import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class Network {

    var counter = 0
    private val client = OkHttpClient()
    var answers = mutableListOf<String>()
    lateinit var correct: String
    lateinit var question: String

     fun makeOkHTTPRequest(amount:String ,category:String ,difficulty:String ,type:String) {
        val urlBuild = HttpUrl.Builder()
            .scheme("https")
            .host("opentdb.com")
            .addPathSegment("api.php")
            .addQueryParameter("amount",amount)
            .addQueryParameter("category", category)
            .addQueryParameter("difficulty",difficulty)
            .addQueryParameter("type",type)
            .build()

        val request = Request.Builder().url(urlBuild).build()
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.v("Fail","fail{${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    val result = Gson().fromJson(jsonString, RequestResult::class.java)

                    correct = result.results[counter].correct_answer
                    answers = mutableListOf<String>()
                    result.results.toMutableList()[counter].incorrect_answers.forEach{
                        answers.add(it)
                        counter++
                    }
                    answers.add(correct)
                    answers.shuffle()
                    question = result.results.toMutableList()[0].question
                    //Log.v("response", "correct:$correct - all:$answers - $counter")

                }
            }
        })
    }
}