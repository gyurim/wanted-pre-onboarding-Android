package com.codelab.newsapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
import java.text.SimpleDateFormat

class RecyclerViewAdapter(private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerViewHolder>() {
    private var articles = emptyList<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArticleBinding.inflate(inflater, parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = articles[position]
        holder.bindTo(item)
        holder.itemView.setOnClickListener {
            item.let { itemClickListener.onItemClick(it) }
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun setItems(newsItem: News) {
        val diffUtil = BaseDiffUtil(articles, newsItem.articles)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        articles = newsItem.articles

        val list: MutableList<Article> = mutableListOf<Article>()

        newsItem.articles.forEach {
            if (!it.urlToImage.isNullOrEmpty()) {
                list.add(it)
            }
        }

        articles = list
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
                Log.d("imageFromUrl", imageUrl.toString())
                view.setImageDrawable(ContextCompat.getDrawable(view.context, R.drawable.item_failed_image))
            }
        }

        @BindingAdapter("bindServerDate")
        @JvmStatic
        fun bindServerDate(view: TextView, publishedAt: String) {
            val format = "yyyy-MM-dd'T'HH:mm:ss'Z'"
            var formatDate = ""
            val date = SimpleDateFormat(format).parse(publishedAt)

            val curTime = System.currentTimeMillis()
            val regTime = date.time
            var diffTime = (curTime - regTime) / 1000

            if (diffTime < SEC) {
                formatDate = "$diffTime seconds ago"
            } else {
                diffTime /= SEC
                if (diffTime < MIN) {
                    formatDate = "$diffTime minutes ago"
                } else {
                    diffTime /= MIN
                    if (diffTime < HOUR) {
                        formatDate = "$diffTime hours ago"
                    } else {
                        diffTime /= HOUR
                        if (diffTime < DAY) {
                            formatDate = "$diffTime days ago"
                        } else {
                            formatDate = SimpleDateFormat("yyyy-MM-dd").format(date)
                        }
                    }
                }
            }
            view.text = formatDate
        }

        const val SEC = 60
        const val MIN = 60
        const val HOUR = 24
        const val DAY = 30
    }

    interface OnItemClickListener {
        fun onItemClick(article: Article)
    }
}