package com.codelab.newsapplication.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.codelab.newsapplication.adapter.RecyclerViewAdapter
import com.codelab.newsapplication.databinding.ActivityCategoryListBinding
import com.codelab.newsapplication.model.Article
import com.codelab.newsapplication.util.NetworkResult
import com.codelab.newsapplication.util.VerticalItemDecorator
import com.codelab.newsapplication.viewmodel.CategoryListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryListActivity : AppCompatActivity()  {
    private val categoryListViewModel: CategoryListViewModel by viewModels()
    private val categoryListAdapter by lazy { RecyclerViewAdapter(onItemClickListener()) }

    private lateinit var binding: ActivityCategoryListBinding

    override fun onCreate(savedInstanceState: Bundle?){
        Log.d("CategoryListActivity", "onCreate")
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryListBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.categoryListViewModel = categoryListViewModel
        binding.categoryListRecyclerview.adapter = categoryListAdapter
        binding.categoryListRecyclerview.addItemDecoration(VerticalItemDecorator(17))
        setContentView(binding.root)
        // Get Intent
        getIntentValue()
    }

    private fun getIntentValue(){
        val category = intent.getStringExtra(EXTRA_CATEGORY_TITLE_DATA)
        if (category != null) {
            getCategoryList(category)
        }
    }

    private fun getCategoryList(category: String) {
        categoryListViewModel.getCategoryNews(category)
        categoryListViewModel.newsList.observe(this, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        categoryListAdapter.setItems(it)
                        Log.d("response", it.toString())
                    }
                }

                is NetworkResult.Error -> {
                    Toast.makeText(applicationContext, response.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun onItemClickListener(): RecyclerViewAdapter.OnItemClickListener {
        val intent = Intent(this, ArticleDetailActivity::class.java)
        return object : RecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(article: Article) {
                intent.putExtra(ArticleDetailActivity.EXTRA_ARTICLE_DATA, article)
                startActivity(intent)
            }
        }
    }
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }


    companion object {
        const val EXTRA_CATEGORY_TITLE_DATA = "category_title_data"
    }
}