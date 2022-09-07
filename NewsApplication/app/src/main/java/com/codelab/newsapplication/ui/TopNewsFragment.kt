package com.codelab.newsapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.codelab.newsapplication.R
import com.codelab.newsapplication.databinding.FragmentTopNewsBinding

class TopNewsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTopNewsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_news, container, false)
        return binding.root
//        return super.onCreateView(inflater, container, savedInstanceState)
    }
}