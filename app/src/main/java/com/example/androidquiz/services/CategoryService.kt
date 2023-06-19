package com.example.androidquiz.services

import com.example.androidquiz.Category

interface CategoryService {
    fun getAllCategories():List<Category>
    fun getCategoryValue(category: String): String
    fun getCategoryTitle(categoryId: String): String
}