package com.example.androidquiz.stats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.androidquiz.R
import com.example.androidquiz.stats.service.StatisticsService
import java.io.File

class StatisticsActivity : AppCompatActivity() {

    lateinit var statisticsFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        statisticsFile = intent.getSerializableExtra("stats") as File

        val statService = StatisticsService(this)
        val receivedStatistics = statService.readObjectFromFile(statisticsFile)
        val questionFragment = StatsFragment(receivedStatistics!!)

        val fragmentManager: FragmentManager = supportFragmentManager

        val transaction = fragmentManager.beginTransaction()

        transaction.add(R.id.fragment_container, questionFragment)

        transaction.commit()
    }
}