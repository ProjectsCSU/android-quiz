package com.example.androidquiz.services

import com.example.androidquiz.Category
import com.example.androidquiz.R
import java.util.*
import javax.inject.Inject

class CategoryServiceImpl @Inject constructor() : CategoryService {
    override fun getAllCategories(): List<Category> {
        return listOf(
            Category("Math", R.drawable.math),
            Category("Biology", R.drawable.math)
        )
    }

    override fun getCategoryValue(category: String): String {
        return when (category.lowercase()) {
            "mythology" -> "20"
            "geography" -> "22"
            "history" -> "23"
            "art" -> "25"
            "sports" -> "21"
            "animals" -> "27"
            else -> throw Exception("Category is not found")
        }
    }
}