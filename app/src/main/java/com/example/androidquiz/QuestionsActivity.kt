package com.example.androidquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager

class QuestionsActivity : AppCompatActivity() {

    lateinit var questionsAndCorrectAnswers: Map<String, String>
    lateinit var questionsAndAnswers: List<QuestionAndAnswersModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        questionsAndAnswers = intent.getSerializableExtra("questAndAns") as ArrayList<QuestionAndAnswersModel>
        questionsAndCorrectAnswers = intent.getSerializableExtra("questAndCor") as Map<String, String>

        val questionFragment = QuestionFragment(questionsAndAnswers[0], questionsAndAnswers.size, 0)

        val fragmentManager: FragmentManager = supportFragmentManager

        val transaction = fragmentManager.beginTransaction()

        transaction.add(R.id.fragment_container, questionFragment)

        transaction.commit()
    }

    private var currentQuestionIndex = 0

    fun onAnswerSelected(question: String, answer: String) {
        val correctAnswer = questionsAndCorrectAnswers[question]
        if (answer == correctAnswer) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show()
        }

        currentQuestionIndex++

        if (currentQuestionIndex < questionsAndAnswers.size) {
            renderFragment(currentQuestionIndex)
        } else {
            renderFragment(currentQuestionIndex - 1)
        }
    }

    private fun renderFragment(questionIndex: Int){
        val questionFragment = QuestionFragment(questionsAndAnswers[questionIndex], questionsAndAnswers.size, questionIndex)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, questionFragment)
            .commit()
    }
}