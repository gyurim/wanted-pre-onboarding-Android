package com.codelab.newsapplication.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.codelab.newsapplication.BuildConfig
import com.codelab.newsapplication.R
import com.codelab.newsapplication.databinding.ActivityMainBinding
import com.codelab.newsapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // bottom navigation 으로 fragment 이동 시, back button 안 나타나게 하기 위함
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainViewModel : MainViewModel by viewModels()
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        // Bottom Navigation
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.menu_top_news, R.id.menu_categories, R.id.menu_saved)
        )
        setSupportActionBar(binding.mainToolbar)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        binding.bottomNavigation.setupWithNavController(navController)
    }
}