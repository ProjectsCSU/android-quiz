package com.example.androidquiz.services

import com.example.androidquiz.Category
import com.example.androidquiz.R
import javax.inject.Inject

class CategoryServiceImpl @Inject constructor() : CategoryService {
    override fun getAllCategories(): List<Category> {
        return listOf(
            Category("Math", R.drawable.math),
            Category("Biology", R.drawable.math)
        )
    }
}