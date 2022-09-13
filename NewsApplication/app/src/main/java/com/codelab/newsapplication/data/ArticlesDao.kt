package com.codelab.newsapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codelab.newsapplication.data.entities.ArticlesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(articlesEntity: ArticlesEntity)

    @Query("SELECT * FROM article ORDER BY id ASC")
    fun getArticles(): Flow<List<ArticlesEntity>>

    @Query("DELETE FROM article WHERE title = :title")
    suspend fun deleteArticle(title: String)

    @Query("SELECT * FROM article WHERE title = :title")
    suspend fun isExistArticle(title: String): ArticlesEntity
}