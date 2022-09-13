package com.codelab.newsapplication.data

import com.codelab.newsapplication.data.entities.ArticlesEntity
import com.codelab.newsapplication.database.ArticlesDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor (
    private val articlesDao: ArticlesDao
){
    fun getArticles(): Flow<List<ArticlesEntity>> {
        return articlesDao.getArticles()
    }

    suspend fun insertArticles(articlesEntity: ArticlesEntity) {
        articlesDao.insertArticle(articlesEntity)
    }

    suspend fun isExistArticle(title: String): ArticlesEntity {
        return articlesDao.isExistArticle(title)
    }

    suspend fun deleteArticle(title: String){
        articlesDao.deleteArticle(title)
    }
}