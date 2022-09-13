package com.codelab.newsapplication.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.codelab.newsapplication.R
import com.codelab.newsapplication.data.entities.ArticlesEntity
import com.codelab.newsapplication.databinding.ActivityArticleDetailBinding
import com.codelab.newsapplication.databinding.ActivityMainBinding
import com.codelab.newsapplication.model.Article
import com.codelab.newsapplication.viewmodel.ArticleDetailViewModel
import com.codelab.newsapplication.viewmodel.TopNewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleDetailBinding
    private val articleDetailViewModel: ArticleDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.articleDetailViewModel = articleDetailViewModel

        getIntentValue()

        setContentView(binding.root)
        setSupportActionBar(binding.articleDetailToolbar)

        // toolbar 속 뒤로가기 버튼 생성
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getIntentValue(){
        val article = intent.getParcelableExtra<Article>(EXTRA_ARTICLE_DATA)

        if (article != null) {
            Log.d("article", article.title)
            binding.articleItem = article
            binding.articleDetailImage.clipToOutline = true
            articleDetailViewModel.articleTitle = article.title
            articleDetailViewModel.isExistArticle(article.title)
            checkSavedArticle()
        } else {
            binding.articleItem = Article("", resources.getString(R.string.failed_to_load_article), "", "", "")
        }
    }

    private fun checkSavedArticle(){
        articleDetailViewModel.saveStatus.observe(this, Observer { state ->
            when(state) {
                true -> {
                    binding.savedButton.isSelected = true
                }
                false -> {
                    binding.savedButton.isSelected = false
                }
            }
        })
    }

    companion object {
        const val EXTRA_ARTICLE_DATA = "article_data"
    }
}