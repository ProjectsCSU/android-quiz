package com.example.androidquiz

import com.example.androidquiz.services.CategoryService
import com.example.androidquiz.services.CategoryServiceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class CategoryModule {
    @Binds
    abstract fun getCategoryService(categoryServiceImpl: CategoryServiceImpl): CategoryService
}