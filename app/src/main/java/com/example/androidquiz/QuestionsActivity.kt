package com.example.androidquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import com.example.androidquiz.stats.service.StatisticsService
import java.io.File

class QuestionsActivity : AppCompatActivity() {

    private lateinit var statisticsFile: File
    lateinit var questionsAndCorrectAnswers: Map<String, String>
    lateinit var questionsAndAnswers: List<QuestionAndAnswersModel>
    lateinit var category: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        val fileName = "stats.txt"
        val directory = this.filesDir
        statisticsFile = File(directory, fileName)
        questionsAndAnswers = intent.getSerializableExtra("questAndAns") as ArrayList<QuestionAndAnswersModel>
        questionsAndCorrectAnswers = intent.getSerializableExtra("questAndCor") as Map<String, String>
        category = intent.getSerializableExtra("category") as String

        val questionFragment = QuestionFragment(questionsAndAnswers[0], questionsAndAnswers.size, 0)

        val fragmentManager: FragmentManager = supportFragmentManager

        val transaction = fragmentManager.beginTransaction()

        transaction.add(R.id.fragment_container, questionFragment)

        transaction.commit()
    }

    private var currentQuestionIndex = 0
    private var correctAnswersAmount = 0

    fun onAnswerSelected(question: String, answer: String) {
        val correctAnswer = questionsAndCorrectAnswers[question]
        if (answer == correctAnswer) {
            correctAnswersAmount++
        }
        if(currentQuestionIndex < questionsAndAnswers.size - 1) {
            currentQuestionIndex++
            renderFragment(currentQuestionIndex)
        } else {
            renderFragment(currentQuestionIndex - 1)
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Quiz is finished, your score: $correctAnswersAmount")
                .setCancelable(false)
                .setPositiveButton("Go to menu") { dialog, which ->
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            val dialog = builder.create()
            dialog.show()
            rewriteStats(category)
        }
    }

    private fun rewriteStats(category: String) {
        val statService = StatisticsService(this)
        val receivedStatistics = statService.readObjectFromFile(statisticsFile)
        if (receivedStatistics != null) {
            for(stat in receivedStatistics.rating){
                if(stat.category == category) {
                    stat.correctAnswersCount += correctAnswersAmount
                    break
                }
            }
            statService.writeObjectToFile(receivedStatistics, statisticsFile)
        }
    }

    private fun renderFragment(questionIndex: Int){
        val questionFragment = QuestionFragment(questionsAndAnswers[questionIndex], questionsAndAnswers.size, questionIndex)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, questionFragment)
            .commit()
    }
}