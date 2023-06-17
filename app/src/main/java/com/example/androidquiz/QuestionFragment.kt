package com.example.androidquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class QuestionFragment(val question: String, val answers: Array<String>) : Fragment() {

    private lateinit var activity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Создаем View, которое содержит наш фрагмент
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        // Получаем MainActivity, в которой размещен этот фрагмент
        activity = getActivity() as MainActivity

        // Вставляем текст в TextView
        val questionTextView = view.findViewById<TextView>(R.id.question_text_view)
        questionTextView.text = question

        // Создаем кнопки с вариантами ответов
        val answerButtonsContainer = view.findViewById<LinearLayout>(R.id.answer_buttons_container)
        for (answer in answers) {
            val button = Button(activity)
            button.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            button.text = answer
            button.setOnClickListener {
                // При нажатии на кнопку вызываем метод в MainActivity для обработки ответа
                activity.onAnswerSelected(question, answer)
            }
            // Генерируем уникальный идентификатор для кнопки
            val buttonId = View.generateViewId()
            button.id = buttonId
            answerButtonsContainer.addView(button)
        }

        return view
    }

}