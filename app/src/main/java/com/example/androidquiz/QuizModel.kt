package com.example.androidquiz

data class QuizModel(val r_questAndAns: MutableList<QuestionAndAnswersModel>,
                     val r_questAndCorAns: HashMap<String, String>)
