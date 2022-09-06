package com.codelab.newsapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codelab.newsapplication.data.NewsRepository

class MainViewModelFactory(
    private val repository: NewsRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass) {
            MainViewModel::class.java -> {
                MainViewModel(repository) as T
            }

            else -> {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}