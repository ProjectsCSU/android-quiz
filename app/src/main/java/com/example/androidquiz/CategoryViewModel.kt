package com.example.androidquiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidquiz.services.CategoryService
import javax.inject.Inject
import javax.inject.Provider

class CategoryViewModel @Inject constructor(categoryService: CategoryService) : ViewModel() {
    val categories = MutableLiveData<List<Category>>()
    init {
        categories.value = categoryService.getAllCategories()
    }
}
class CategoryViewModelFactory @Inject constructor(private val myViewModelProvider: Provider<CategoryViewModel>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return myViewModelProvider.get() as T
    }
}