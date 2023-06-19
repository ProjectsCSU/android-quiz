package com.example.androidquiz.stats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.androidquiz.R
import com.example.androidquiz.stats.model.StatisticsDto


class StatsFragment(val statistics: StatisticsDto) : Fragment() {

    private lateinit var activity: StatisticsActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)

        activity = getActivity() as StatisticsActivity

        val statsContainer = view.findViewById<LinearLayout>(R.id.stats_container)
        for (stat in statistics.rating) {
            val textView = TextView(activity)
            textView.textSize = 24F
            textView.text = "${stat.category}           Score: ${stat.correctAnswersCount}"
            statsContainer.addView(textView)
        }
        return view
    }
}