package com.codelab.newsapplication.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.newsapplication.data.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val repository: NewsRepository
) : ViewModel(){
    fun getNews(queries: Map<String, String>) = viewModelScope.launch {
        // for testing
        getNewsSafeCall(queries)
    }

    private suspend fun getNewsSafeCall(queries: Map<String, String>) {
        val response = repository.remote.getNews(queries)
        Log.d("뉴스", response.body().toString())
    }
}