package com.codelab.newsapplication.adapter

import androidx.recyclerview.widget.RecyclerView
import com.codelab.newsapplication.databinding.ItemArticleBinding
import com.codelab.newsapplication.model.Article

class TopNewsViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindTo(article: Article?) {
        with(binding) {
            articleItem = article
        }
    }
}