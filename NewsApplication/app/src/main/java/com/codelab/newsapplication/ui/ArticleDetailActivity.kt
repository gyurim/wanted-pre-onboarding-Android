package com.codelab.newsapplication.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.codelab.newsapplication.R
import com.codelab.newsapplication.databinding.ActivityArticleDetailBinding
import com.codelab.newsapplication.databinding.ActivityMainBinding
import com.codelab.newsapplication.model.Article

class ArticleDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)

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
            binding.articleItem = article
            binding.articleDetailImage.clipToOutline = true
        } else {
            binding.articleItem = Article("", resources.getString(R.string.failed_to_load_article), "", "", "")
        }
    }

    companion object {
        const val EXTRA_ARTICLE_DATA = "article_data"
    }
}