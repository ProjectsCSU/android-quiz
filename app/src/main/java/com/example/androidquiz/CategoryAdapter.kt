package com.example.androidquiz

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidquiz.services.QuestionRequestsService

class CategoryAdapter() : ListAdapter<Category, CategoryAdapter.CategoryHolder>(CategoryComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            val service = QuestionRequestsService()
            val response = service.getQuestions("10", "20", "easy")
            val questionsModel = service.serializeQuestions(response)
            val r_questAndAns: MutableList<QuestionAndAnswersModel> = mutableListOf()
            val r_questAndCorAns: HashMap<String, String> = hashMapOf()

            for (model in questionsModel.results) {
                val answers: MutableList<String> = mutableListOf(model.correct_answer)
                for(incorrectAnswer in model.incorrect_answers) {
                    answers.add(incorrectAnswer)
                }
                val questAndAnsw = QuestionAndAnswersModel(model.question, answers)
                r_questAndAns.add(questAndAnsw)
                r_questAndCorAns.put(model.question, model.correct_answer)
            }
            val intent = Intent(holder.itemView.context, QuestionsActivity::class.java)
            intent.putExtra("questAndAns", ArrayList(r_questAndAns))
            intent.putExtra("questAndCor", HashMap(r_questAndCorAns))
            holder.itemView.context.startActivity(intent)
        }
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