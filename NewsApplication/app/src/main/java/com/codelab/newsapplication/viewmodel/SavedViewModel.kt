package com.codelab.newsapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.codelab.newsapplication.data.NewsRepository
import com.codelab.newsapplication.data.entities.ArticlesEntity
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor (
    private val repository: NewsRepository
) : ViewModel() {

    // LocalData
    val savedNewsList: LiveData<List<ArticlesEntity>> = repository.local.getArticles().asLiveData()
}
