package com.example.androidquiz

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidquiz.dtos.QuestionDto
import com.example.androidquiz.services.CategoryService
import com.example.androidquiz.services.QuestionRequestsService
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class CategoryAdapter: ListAdapter<Category, CategoryAdapter.CategoryHolder>(CategoryComparator()) {
    @Inject
    lateinit var categoryService: CategoryService
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder.create(parent, viewType)
    }
    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(getItem(position))
        DaggerAppComponent.create().injectCategoryAdapter(this)
        holder.itemView.setOnClickListener {
            val difficultyItems = arrayOf("Easy", "Medium", "Hard")
            val selectedQuestionCount = 10
            var selectedDifficulty = ""

            AlertDialog.Builder(it.context)
                .setTitle("Enter difficulty")
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton("Start test") { _, _ ->
                    moveToActivity(it, selectedQuestionCount, selectedDifficulty)
                }
                .setSingleChoiceItems(difficultyItems, -1) { _, which ->
                    selectedDifficulty = when (which) {
                        0 -> "easy"
                        1 -> "medium"
                        2 -> "hard"
                        else -> ""
                    }
                }.show()
        }
    }
    private fun moveToActivity(it: View, selectedQuestionCount: Int, selectedDifficulty: String) {
        try {
            val service = QuestionRequestsService()
            val title = it.findViewById<TextView>(R.id.title).text.toString()
            val response = service.getQuestions(
                selectedQuestionCount.toString(),
                categoryService.getCategoryValue(title),
                selectedDifficulty.lowercase())
            val questionsModel : QuestionDto
            runBlocking {
                val result = response.await()
                questionsModel = service.serializeQuestions(result)
            }
            val questionsAndAnswers = getQuestionsAndAnswers(questionsModel)

            val intent = Intent(it.context, QuestionsActivity::class.java)
            intent.putExtra("questAndAns", ArrayList(questionsAndAnswers.r_questAndAns))
            intent.putExtra("questAndCor", HashMap(questionsAndAnswers.r_questAndCorAns))
            intent.putExtra("category", categoryService.getCategoryTitle(categoryService.getCategoryValue(title)))
            it.context.startActivity(intent)
        } catch(e: Exception) {
            print(e.message)
            val intent = Intent(it.context, MainActivity::class.java)
            it.context.startActivity(intent)
        }
    }
    private fun getQuestionsAndAnswers(questionsModel: QuestionDto): QuizModel {
        val questionsAndAnswers: MutableList<QuestionAndAnswersModel> = mutableListOf()
        val questionsAndCorrectAnswers: HashMap<String, String> = hashMapOf()
        for (model in questionsModel?.results!!) {
            val answers: MutableList<String> = mutableListOf(model.correct_answer)
            for(incorrectAnswer in model.incorrect_answers) {
                answers.add(incorrectAnswer)
            }
            answers.shuffle()
            val questionAndAnswer = QuestionAndAnswersModel(model.question, answers)
            questionsAndAnswers.add(questionAndAnswer)
            questionsAndCorrectAnswers[model.question] = model.correct_answer
        }
        return QuizModel(questionsAndAnswers, questionsAndCorrectAnswers)
    }
    class CategoryHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        private val title by lazy {itemView.findViewById<TextView>(R.id.title)}
        private val image by lazy {itemView.findViewById<ImageView>(R.id.media)}

        companion object{
            fun create(parent: ViewGroup, viewType: Int): CategoryHolder {
                return CategoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_card, parent, false))
            }
        }
        fun bind(category: Category) {
            title.text = category.title
            image.setImageResource(category.image)
        }
    }

    class CategoryComparator : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
}