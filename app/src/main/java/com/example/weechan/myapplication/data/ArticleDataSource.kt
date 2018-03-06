package com.example.weechan.myapplication.data

import com.example.weechan.myapplication.bean.ArticleDetial
import com.example.weechan.myapplication.data.local.ArticleLocalDataSource
import java.util.*

/**
 * Created by steve on 18-3-4.
 */
interface ArticleDataSource {

    fun saveArticle(article:ArticleDetial)
    fun deleteArticle(article:ArticleDetial)
    fun deleteAllArticles()
    fun getArticles(count : Int , callback:ArticleLocalDataSource.LoadArticlesCallback)
    fun downMoreArticle(callback: ArticleLocalDataSource.LoadArticleCallback)
}