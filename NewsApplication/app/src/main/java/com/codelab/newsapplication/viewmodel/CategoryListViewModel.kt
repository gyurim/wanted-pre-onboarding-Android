package com.codelab.newsapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.newsapplication.BuildConfig
import com.codelab.newsapplication.data.NewsRepository
import com.codelab.newsapplication.model.News
import com.codelab.newsapplication.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel  @Inject constructor (
    private val repository: NewsRepository
) : ViewModel() {
    private val _newsList: MutableLiveData<NetworkResult<News>> = MutableLiveData()
    val newsList: LiveData<NetworkResult<News>> = _newsList

    fun getCategoryNews(category: String) = viewModelScope.launch {
        getCategoryNewSafeCall(category)
    }

    private suspend fun getCategoryNewSafeCall(category: String){
        try {
            val response = repository.remote.getCategoryNews(country = "us", category= category, apiKey = BuildConfig.NEWS_API_KEY)
            if (response.body() != null) {
                _newsList.value = NetworkResult.Success(response.body())
            }
        } catch (e: Exception) {
            _newsList.value = NetworkResult.Error("Error Occurred")
        }
    }

}