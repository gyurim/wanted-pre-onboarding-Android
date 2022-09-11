package com.codelab.newsapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.codelab.newsapplication.databinding.FragmentTopNewsBinding
import com.codelab.newsapplication.databinding.ItemArticleBinding
import com.codelab.newsapplication.model.Article
import com.codelab.newsapplication.model.News
import com.codelab.newsapplication.util.BaseDiffUtil

class TopNewsAdapter : RecyclerView.Adapter<TopNewsViewHolder>() {
    private var articles = emptyList<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopNewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArticleBinding.inflate(inflater, parent, false)
        return TopNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopNewsViewHolder, position: Int) {
        val item = articles.get(position)
        holder.bindTo(item)
    }

    override fun getItemCount(): Int {
        Log.d("탑 뉴스", articles.size.toString())
        return articles.size
    }

    fun setItems(newsItem: News) {
        val diffUtil = BaseDiffUtil(articles, newsItem.articles)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        articles = newsItem.articles

        Log.d("뉴스", articles.toString())
        Log.d("뉴스 개수", articles.size.toString())
        diffUtilResult.dispatchUpdatesTo(this)
    }
}