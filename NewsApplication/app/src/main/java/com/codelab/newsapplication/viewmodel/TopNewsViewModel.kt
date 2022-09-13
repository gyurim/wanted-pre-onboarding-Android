package com.codelab.newsapplication.viewmodel

import androidx.lifecycle.*
import com.codelab.newsapplication.data.NewsRepository
import com.codelab.newsapplication.model.News
import com.codelab.newsapplication.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopNewsViewModel @Inject constructor (
    private val repository: NewsRepository
) : ViewModel(){

    // RemoteData
    private val _newsList: MutableLiveData<NetworkResult<News>> = MutableLiveData()
    val newsList: LiveData<NetworkResult<News>> = _newsList

    fun getNews(queries: Map<String, String>) = viewModelScope.launch {
        getNewsSafeCall(queries)
    }

    private suspend fun getNewsSafeCall(queries: Map<String, String>) {
        try {
            val response = repository.remote.getNews(queries)
            if (response.body() != null) {
                _newsList.value = NetworkResult.Success(response.body())
            }
        } catch (e: Exception) {
            _newsList.value = NetworkResult.Error("Error Occurred")
        }
    }

}