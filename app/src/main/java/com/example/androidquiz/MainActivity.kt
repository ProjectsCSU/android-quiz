package com.example.androidquiz

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.androidquiz.services.QuestionRequestsService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of getting questions!!!! toDo: Make parameters choice
        val service = QuestionRequestsService()
        val response = service.getQuestions("10", "20", "easy")
        val questionsModel = service.serializeQuestions(response)
        val r_questAndAns: MutableList<QuestionAndAnswersModel> = mutableListOf()
        val r_questAndCorAns: HashMap<String, String> = hashMapOf()

        for (model in questionsModel.results) {
            val answers: MutableList<String> = mutableListOf(model.correct_answer)
            for(incorrectAnswer in model.incorrect_answers) {
                answers.add(incorrectAnswer)
            }
            val questAndAnsw = QuestionAndAnswersModel(model.question, answers)
            r_questAndAns.add(questAndAnsw)
            r_questAndCorAns.put(model.question, model.correct_answer)
        }

        val startGameButton: Button = findViewById(R.id.start_button)
        val statisticsButton: Button = findViewById(R.id.statistics_button)
        val exitButton: Button = findViewById(R.id.exit_button)

        startGameButton.setOnClickListener {
            val intent = Intent(this, CategorySelectActivity::class.java)

//            intent.putExtra("questAndAns", ArrayList(r_questAndAns))
//            intent.putExtra("questAndCor", HashMap(r_questAndCorAns))

            startActivity(intent)
        }

        statisticsButton.setOnClickListener {
            // Действия, выполняемые при нажатии на кнопку "Статистика"
        }

        exitButton.setOnClickListener {
            finish() // Завершение приложения при нажатии на кнопку "Выйти"
        }
    }
}