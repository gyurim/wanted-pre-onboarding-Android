package com.codelab.newsapplication.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.codelab.newsapplication.BuildConfig
import com.codelab.newsapplication.R
import com.codelab.newsapplication.data.NewsRepository
import com.codelab.newsapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val mainViewModel : MainViewModel by viewModels()
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        mainViewModel.getNews(mapOf("us" to BuildConfig.NEWS_API_KEY))
    }
}