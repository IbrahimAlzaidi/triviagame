package com.example.triviagame.data

data class RequestResult(
    val response_code:Int,
    val results: MutableList<ResultType>
)


