package com.codelab.newsapplication.di

import android.content.Context
import androidx.room.Room
import com.codelab.newsapplication.database.ArticlesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun getDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ArticlesDatabase::class.java,
        "article.db"
    ).build()

    @Singleton
    @Provides
    fun getArticlesDao(database: ArticlesDatabase) = database.articlesDao()
}