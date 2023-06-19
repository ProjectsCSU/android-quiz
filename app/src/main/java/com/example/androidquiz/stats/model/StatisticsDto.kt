package com.example.androidquiz.stats.model

data class StatisticsDto(val rating: List<CategoryRating>) {
    data class CategoryRating(val category: String, var correctAnswersCount: Int)
}
