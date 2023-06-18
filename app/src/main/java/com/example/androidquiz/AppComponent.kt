package com.example.androidquiz

import dagger.Component

@Component(modules = [CategoryModule::class, ViewModule::class])
interface AppComponent {
    fun injectFragment(categoryFragment: CategoryFragment)
}