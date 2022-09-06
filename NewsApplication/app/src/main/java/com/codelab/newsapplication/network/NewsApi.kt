package com.codelab.newsapplication.network

import com.codelab.newsapplication.model.News
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NewsApi {
    @GET("/v2/top-headlines")
    suspend fun getNewsList(
        @QueryMap queries: Map<String, String>
    ): Response<News>

    @GET("/v2/top-headlines")
    suspend fun getCategoryNewsList(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Response<News>
}