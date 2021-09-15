package com.example.triviagame.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.triviagame.R
import com.example.triviagame.data.RequestResult
import com.example.triviagame.databinding.ActivityMainBinding
import com.example.triviagame.databinding.NewGameFragmentBinding
import com.example.triviagame.ui.fragments.NewGameFragment
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var counter = 0
   // lateinit var result :Gson
    private val client = OkHttpClient()
    private val newGameFragment = NewGameFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSubView()

        val value1 =    "10"
        val value2 =    "10"
        val value3 =    "easy"
        val value4 =    "multiple"
        makeOkHTTPRequest( value1, value2, value3,value4 )
    }

    private fun initSubView() {
        val transaction = supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, newGameFragment)
            .commit()
    }

    private fun makeOkHTTPRequest(amount:String ,category:String ,difficulty:String ,type:String) {
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
            client.newCall(request).enqueue(object : Callback{

            override fun onFailure(call: Call, e: IOException) {
               Log.v("Fail","fail{${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                   val result = Gson().fromJson(jsonString, RequestResult::class.java)

                    val correct = result.results[counter].correct_answer
                    val incorrect = result.results.toMutableList()[counter].incorrect_answers.toString()
                    val answers = mutableListOf<String>()
                    result.results.toMutableList()[counter].incorrect_answers.forEach{
                        answers.add(it)
                    }
                    answers.add(correct)
                        //answers.add(correct)
                        //answers.add(incorrect)
                    answers.shuffle()
                    val question = result.results.toMutableList()[0].question
                        Log.v("response", "correct:$correct- all:$answers")

                }
            }
        })
    }

}