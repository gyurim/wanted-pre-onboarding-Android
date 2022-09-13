package com.codelab.newsapplication.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.codelab.newsapplication.BuildConfig
import com.codelab.newsapplication.adapter.RecyclerViewAdapter
import com.codelab.newsapplication.databinding.FragmentTopNewsBinding
import com.codelab.newsapplication.model.Article
import com.codelab.newsapplication.ui.ArticleDetailActivity
import com.codelab.newsapplication.util.NetworkResult
import com.codelab.newsapplication.util.VerticalItemDecorator
import com.codelab.newsapplication.viewmodel.TopNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopNewsFragment : Fragment() {
    private val topNewsViewModel: TopNewsViewModel by viewModels()
    private val topNewsAdapter by lazy { RecyclerViewAdapter(onItemClickListener()) }

    private var _binding: FragmentTopNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopNewsBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.topNewsViewModel = topNewsViewModel
        binding.topNewsRecyclerview.adapter = topNewsAdapter
        binding.topNewsRecyclerview.addItemDecoration(VerticalItemDecorator(17))

        observeLiveData()

        return binding.root
    }

    private fun observeLiveData() {
        getNews()
    }

    private fun getQueries() : HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries["country"] = "us"
        queries["apiKey"] = BuildConfig.NEWS_API_KEY
        return queries
    }

    private fun getNews(){
        lifecycleScope.launch {
            topNewsViewModel.getNews(getQueries())
            topNewsViewModel.newsList.observe(viewLifecycleOwner, { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        response.data?.let {
                            topNewsAdapter.setItems(it)
                        }
                    }

                    is NetworkResult.Error -> {
                        Toast.makeText(requireContext(), response.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun onItemClickListener(): RecyclerViewAdapter.OnItemClickListener {
        val intent = Intent(this.context, ArticleDetailActivity::class.java)
        return object : RecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(article: Article) {
                intent.putExtra(ArticleDetailActivity.EXTRA_ARTICLE_DATA, article)
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}