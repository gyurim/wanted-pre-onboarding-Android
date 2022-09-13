package com.codelab.newsapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.newsapplication.BuildConfig
import com.codelab.newsapplication.data.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoriesViewModel  @Inject constructor (
    private val repository: NewsRepository
) : ViewModel() {
}