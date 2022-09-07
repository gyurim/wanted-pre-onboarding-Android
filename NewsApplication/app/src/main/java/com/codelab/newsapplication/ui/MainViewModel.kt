package com.codelab.newsapplication.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.newsapplication.data.NewsRepository
import com.codelab.newsapplication.model.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val repository: NewsRepository
) : ViewModel(){
    private val newsList: MutableLiveData<Result<News>> = MutableLiveData()
    fun getNews(queries: Map<String, String>) = viewModelScope.launch {
        // for testing
        getNewsSafeCall(queries)
    }

    private suspend fun getNewsSafeCall(queries: Map<String, String>) {
        val response = repository.remote.getNews(queries)
        newsList.let {
            Result.success(response.body())
        }
    }
}