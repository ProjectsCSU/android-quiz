package com.example.androidquiz

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.androidquiz.Dtos.QuestionDto
import java.net.URL
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Создаем экземпляр QuestionFragment
        val questionFragment = QuestionFragment(questions[0], answers, questions.size, 0)

        // Получаем экземпляр FragmentManager для управления фрагментом
        val fragmentManager: FragmentManager = supportFragmentManager

        // Получаем экземпляр FragmentTransaction для управления транзакцией фрагмента
        val transaction = fragmentManager.beginTransaction()

        // Добавляем QuestionFragment в контейнер
        transaction.add(R.id.fragment_container, questionFragment)

        // Подтверждаем транзакцию
        transaction.commit()
    }
    private val questions = arrayOf(
        "What is the capital of France?",
        "What is the currency of Japan?",
        "What is the largest country by area in the world?"
    )

    private val questionsAndAnswers = mapOf(
        "What is the capital of France?" to "Paris",
        "What is the currency of Japan?" to "Yen",
        "What is the largest country by area in the world?" to "Russia"
    )

    private val answers = arrayOf("Paris", "Yen", "Russia")

    private var currentQuestionIndex = 0

    fun onAnswerSelected(question: String, answer: String) {

        if (answer == questionsAndAnswers[question]) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show()
        }
        // Переходим к следующему вопросу
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            val questionFragment = QuestionFragment(questions[currentQuestionIndex], answers, questions.size, currentQuestionIndex)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, questionFragment)
                .commit()
        } else {
            Toast.makeText(this, "Quiz is finished", Toast.LENGTH_SHORT).show()
            val questionFragment = QuestionFragment(questions[currentQuestionIndex - 1], answers, questions.size, currentQuestionIndex)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, questionFragment)
                .commit()
        }
    }

}