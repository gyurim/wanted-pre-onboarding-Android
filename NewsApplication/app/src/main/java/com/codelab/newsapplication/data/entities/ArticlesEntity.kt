package com.codelab.newsapplication.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import com.codelab.newsapplication.model.Article

@Entity(tableName = "article")
data class ArticlesEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "publishedAt") val publishedAt: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "urlToImage") val urlToImage: String
): Serializable
