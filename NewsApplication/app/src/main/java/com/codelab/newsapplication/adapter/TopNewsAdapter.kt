package com.codelab.newsapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.codelab.newsapplication.R
import com.codelab.newsapplication.databinding.ItemArticleBinding
import com.codelab.newsapplication.model.Article
import com.codelab.newsapplication.model.News
import com.codelab.newsapplication.util.BaseDiffUtil

class TopNewsAdapter(private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<TopNewsViewHolder>() {
    private var articles = emptyList<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopNewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArticleBinding.inflate(inflater, parent, false)
        return TopNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopNewsViewHolder, position: Int) {
        val item = articles[position]
        holder.bindTo(item)
        holder.itemView.setOnClickListener {
            item?.let { itemClickListener.onItemClick(it) }
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun setItems(newsItem: News) {
        val diffUtil = BaseDiffUtil(articles, newsItem.articles)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        articles = newsItem.articles
        diffUtilResult.dispatchUpdatesTo(this)
    }

    companion object {
        @BindingAdapter("imageFromUrl")
        @JvmStatic
        fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
            if (!imageUrl.isNullOrEmpty()) {
                Glide.with(view.context)
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view)
            } else {
                view.setImageDrawable(ContextCompat.getDrawable(view.context, R.drawable.item_test_image))
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(article: Article)
    }
}