package com.example.androidquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.LinearProgressIndicator

class QuestionFragment(
    val questionAndAnswers: QuestionAndAnswersModel,
    val questionsAmount : Int,
    val currentQuestionIndex: Int) : Fragment() {

    private lateinit var activity: QuestionsActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        activity = getActivity() as QuestionsActivity

        val questionTextView = view.findViewById<TextView>(R.id.question_text_view)
        questionTextView.text = questionAndAnswers.question

        val answerButtonsContainer = view.findViewById<LinearLayout>(R.id.answer_buttons_container)
        for (answer in questionAndAnswers.answers) {
            val button = MaterialButton(activity)

            button.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            button.text = answer
            button.setOnClickListener {
                activity.onAnswerSelected(questionAndAnswers.question, answer)
            }
            answerButtonsContainer.addView(button)
        }

        val progressBar = LinearProgressIndicator(activity, null)
        answerButtonsContainer.addView(progressBar)

        progressBar.progress = getProgress().toInt()

        return view
    }
    
    private fun getProgress() : Float {
        val progress = currentQuestionIndex
        val maxProgress = questionsAmount
        return progress / maxProgress.toFloat() * 100
    }

}