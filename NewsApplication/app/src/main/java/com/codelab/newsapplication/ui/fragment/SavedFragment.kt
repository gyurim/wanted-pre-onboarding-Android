package com.codelab.newsapplication.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.codelab.newsapplication.R
import com.codelab.newsapplication.adapter.RecyclerViewAdapter
import com.codelab.newsapplication.databinding.FragmentSavedBinding
import com.codelab.newsapplication.databinding.FragmentTopNewsBinding
import com.codelab.newsapplication.model.Article
import com.codelab.newsapplication.ui.ArticleDetailActivity
import com.codelab.newsapplication.util.VerticalItemDecorator
import com.codelab.newsapplication.viewmodel.SavedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedFragment : Fragment() {
    private val savedViewModel: SavedViewModel by viewModels()
    private val savedAdapter by lazy { RecyclerViewAdapter(onItemClickListener()) }

    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.savedViewModel = savedViewModel
        binding.savedRecyclerview.adapter = savedAdapter
        binding.savedRecyclerview.addItemDecoration(VerticalItemDecorator(17))

        loadSaveNews()

        return binding.root
    }

    private fun loadSaveNews(){
        Log.d("loadSaveNews", "호출")
        lifecycleScope.launch {
            savedViewModel.savedNewsList.observe(viewLifecycleOwner, {
                if (it.isNotEmpty()) {
                    savedAdapter.setSavedItem(it)
                }
            })
        }
    }

    private fun onItemClickListener(): RecyclerViewAdapter.OnItemClickListener {
        val intent = Intent(this.context, ArticleDetailActivity::class.java)
        return object : RecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(article: Article) {
                Log.d("startActivity", "호출")
                intent.putExtra(ArticleDetailActivity.EXTRA_ARTICLE_DATA, article)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        savedAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}