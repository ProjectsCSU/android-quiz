package com.example.androidquiz.stats

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
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
            val statCard = generateStatCard(stat.category, stat.correctAnswersCount.toString())
            statsContainer.addView(statCard)
        }
        return view
    }

    private fun generateStatCard(category: String, points: String): LinearLayout {
        // Создаем главный layout
        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.HORIZONTAL
        layout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        // Создаем TextView для отображения категории
        val categoryTextView = TextView(context)
        categoryTextView.text = category
        categoryTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36F)
        categoryTextView.setTextColor(ContextCompat.getColor(activity, R.color.black))
        categoryTextView.layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1F
        )
        categoryTextView.gravity = Gravity.START or Gravity.CENTER_VERTICAL

        // Создаем TextView для отображения количества очков
        val pointsTextView = TextView(context)
        pointsTextView.text = points.toString()
        pointsTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36F)
        pointsTextView.setTextColor(ContextCompat.getColor(activity, R.color.black))
        pointsTextView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        pointsTextView.gravity = Gravity.END or Gravity.CENTER_VERTICAL

        layout.addView(categoryTextView)
        layout.addView(pointsTextView)
        return layout
    }
}