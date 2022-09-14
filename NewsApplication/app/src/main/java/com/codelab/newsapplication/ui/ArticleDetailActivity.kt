package com.codelab.newsapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.codelab.newsapplication.R
import com.codelab.newsapplication.databinding.ActivityArticleDetailBinding
import com.codelab.newsapplication.model.Article
import com.codelab.newsapplication.viewmodel.ArticleDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailActivity : AppCompatActivity() {
    private val articleDetailViewModel: ArticleDetailViewModel by viewModels()
    private var articleTitle : String = ""

    private lateinit var binding: ActivityArticleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this

        binding.articleDetailViewModel = articleDetailViewModel
        setContentView(binding.root)

        getIntentValue()
        setSupportActionBar(binding.articleDetailToolbar)

        // toolbar 속 back button 생성
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = articleTitle
        }
    }

    private fun getIntentValue(){
        if (intent.hasExtra(EXTRA_ARTICLE_DATA)) {
            val article = intent.getParcelableExtra<Article>(EXTRA_ARTICLE_DATA)

            if (article != null) {
                Log.d("article", article.title)
                binding.articleItem = article
                binding.articleDetailImage.clipToOutline = true
                articleDetailViewModel.articleTitle = article.title

                articleDetailViewModel.isExistArticle(article.title)
                articleTitle = article.title
                checkSavedArticle()
            } else {
                binding.articleItem = Article("", resources.getString(R.string.failed_to_load_article), "", "", "")
            }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            // back button
            android.R.id.home -> {
                finish()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_ARTICLE_DATA = "article_data"
    }
}