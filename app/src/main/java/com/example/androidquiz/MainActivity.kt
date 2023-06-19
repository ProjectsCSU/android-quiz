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

        val startGameButton: Button = findViewById(R.id.start_button)
        val statisticsButton: Button = findViewById(R.id.statistics_button)
        val exitButton: Button = findViewById(R.id.exit_button)

        startGameButton.setOnClickListener {
            val intent = Intent(this, CategorySelectActivity::class.java)
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