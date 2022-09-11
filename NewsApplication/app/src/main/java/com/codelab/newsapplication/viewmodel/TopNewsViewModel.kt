package com.codelab.newsapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopNewsViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

}