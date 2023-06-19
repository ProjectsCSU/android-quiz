package com.example.androidquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.androidquiz.stats.StatisticsActivity
import com.example.androidquiz.stats.service.StatisticsService
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
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
            val fileName = "stats.txt"
            val directory = this.filesDir
            val file = File(directory, fileName)
            try {
                if(!file.exists()){
                    file.createNewFile()
                    val statService = StatisticsService(this)
                    statService.writeObjectToFile(statService.initStatistics(), file)
                }
            } catch (ex: IOException) {
            }
            val intent = Intent(this, StatisticsActivity::class.java)
            intent.putExtra("stats", file)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finish()
        }
    }
}