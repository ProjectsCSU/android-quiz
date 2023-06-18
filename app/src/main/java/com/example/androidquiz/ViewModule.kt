package com.example.androidquiz

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ViewModule {
    @Provides
    fun provideMyViewModelFactory(myViewModelProvider: Provider<CategoryViewModel>): CategoryViewModelFactory {
        return CategoryViewModelFactory(myViewModelProvider)
    }

    @Provides
    fun provideMyViewModel(myViewModelFactory: CategoryViewModelFactory): ViewModelProvider.Factory {
        return myViewModelFactory
    }
}