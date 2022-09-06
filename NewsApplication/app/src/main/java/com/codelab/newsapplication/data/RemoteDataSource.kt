package com.codelab.newsapplication.data

import com.codelab.newsapplication.model.News
import com.codelab.newsapplication.network.NewsApi
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val newsApi: NewsApi
){
    suspend fun getNews(queries: Map<String, String>): Response<News> {
        return newsApi.getNewsList(queries)
    }

    suspend fun getCategoryNews(country: String, category: String, apiKey: String):Response<News> {
        return newsApi.getCategoryNewsList(country, category, apiKey)
    }
}