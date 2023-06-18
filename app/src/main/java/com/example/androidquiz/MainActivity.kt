package com.example.androidquiz

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.widget.Button
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

        val button = findViewById<Button>(R.id.button)
        val questAndCor = mapOf("123" to "1234",
                                "456" to "4567")
        val questAndAns = listOf(QuestionAndAnswersModel("123", arrayOf("1234", "45678")),
                                  QuestionAndAnswersModel("456", arrayOf("1234", "4567")))
        button.setOnClickListener {

            val intent = Intent(this, QuestionsActivity::class.java)

            intent.putExtra("questAndAns", ArrayList(questAndAns))
            intent.putExtra("questAndCor", HashMap(questAndCor))

            startActivity(intent)
        }
    }
}