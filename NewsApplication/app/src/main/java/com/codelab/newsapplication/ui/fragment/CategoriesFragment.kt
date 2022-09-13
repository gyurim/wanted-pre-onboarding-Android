package com.codelab.newsapplication.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codelab.newsapplication.R
import com.codelab.newsapplication.databinding.FragmentCategoriesBinding
import com.codelab.newsapplication.ui.CategoryListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.categoriesFragment = this

        return binding.root
    }

    fun clickCategoryButton(view: View) {
        var categoryTitle = ""
        when(view.id) {
            R.id.categories_business_button -> categoryTitle = "business"
            R.id.categories_entertain_button -> categoryTitle = "entertainment"
            R.id.categories_general_button -> categoryTitle = "general"
            R.id.categories_health_button -> categoryTitle = "health"
            R.id.categories_science_button -> categoryTitle = "science"
            R.id.categories_sports_button -> categoryTitle = "sports"
            R.id.categories_technology_button -> categoryTitle = "technology"
        }
        passIntent(categoryTitle)
        Log.d("categories", categoryTitle)
    }

    private fun passIntent(category: String){
        val intent = Intent(this.context, CategoryListActivity::class.java)
        intent.putExtra(CategoryListActivity.EXTRA_CATEGORY_TITLE_DATA, category)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}