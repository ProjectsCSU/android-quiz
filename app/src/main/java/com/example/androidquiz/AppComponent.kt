package com.example.androidquiz

import dagger.Component

@Component(modules = [CategoryModule::class])
interface AppComponent {
    fun injectCategoryFragment(categoryFragment: CategoryFragment)
}