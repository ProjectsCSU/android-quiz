package com.example.androidquiz.dtos

data class QuestionDto(val response_code: Int,
                  val results: List<ResultsItem>) {
    data class ResultsItem(val category: String,
                      val type: String,
                      val difficulty: String,
                      val question: String,
                      val correct_answer: String,
                      val incorrect_answers: List <String>,)
    {

    }
}