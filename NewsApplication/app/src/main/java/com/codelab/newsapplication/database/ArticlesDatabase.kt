package com.codelab.newsapplication.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codelab.newsapplication.app.NewsApplication
import com.codelab.newsapplication.data.ArticlesDao
import com.codelab.newsapplication.data.entities.ArticlesEntity

@Database(entities = [ArticlesEntity::class], version = 1)
abstract class ArticlesDatabase: RoomDatabase() {
    abstract fun articlesDao(): ArticlesDao

//    companion object {
//        val instance = Room.databaseBuilder(
//            NewsApplication.instance,
//            ArticlesDatabase::class.java,
//            "article.db"
//        )
//            .fallbackToDestructiveMigration()
//            .allowMainThreadQueries()
//            .build()
//    }
}

