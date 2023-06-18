package com.example.androidquiz

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidquiz.databinding.FragmentCategoryBinding
import javax.inject.Inject

class CategoryFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var viewModel: CategoryViewModel
    private val categoryAdapter = CategoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        DaggerAppComponent.create().injectFragment(this)

        val binding: FragmentCategoryBinding = FragmentCategoryBinding.inflate(inflater, container, false)

        binding.items.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }

        viewModel.categories.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(listOf(
                Category("Math", R.drawable.math),
                Category("Geography", R.drawable.geography),
                Category("History", R.drawable.math),
                Category("Art", R.drawable.math),
                Category("Sports", R.drawable.math),
                Category("Animals", R.drawable.math))
            )
        }

        return binding.root
    }

}