package com.codelab.newsapplication.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.codelab.newsapplication.data.NewsRepository
import com.codelab.newsapplication.data.entities.ArticlesEntity
import com.codelab.newsapplication.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
    lateinit var articleTitle: String

    // LocalData
    private var _saveStatus = MutableLiveData<Boolean>(false) // 처음 상태
    var saveStatus: LiveData<Boolean> = _saveStatus

    fun savedArticle(article: Article){
        viewModelScope.launch {
            if(_saveStatus.value == false) {
                val entity = ArticlesEntity(title = article.title, author = article.author, publishedAt = article.publishedAt, content = article.content, urlToImage = article.urlToImage)
                insertNews(entity)
                _saveStatus.value = true
            } else {
                deleteNews(article.title)
                _saveStatus.value = false
            }
        }
    }

    private fun insertNews(articlesEntity: ArticlesEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertArticles(articlesEntity)
        }
    }

    private fun deleteNews(title: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.deleteArticle(title)
        }
    }

    fun isExistArticle(title: String){
        viewModelScope.launch {
            try {
                val entity = repository.local.isExistArticle(title)
                Log.d("click", "${entity.id}, ${entity.title}")
                _saveStatus.value = true
            } catch (e: Throwable) {
                _saveStatus.value = false
            }
        }
    }
}