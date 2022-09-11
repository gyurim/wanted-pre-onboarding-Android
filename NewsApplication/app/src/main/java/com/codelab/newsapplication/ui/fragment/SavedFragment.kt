package com.codelab.newsapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.codelab.newsapplication.R
import com.codelab.newsapplication.databinding.FragmentSavedBinding
import com.codelab.newsapplication.databinding.FragmentTopNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment() {
    private var _binding: FragmentSavedBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSavedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}