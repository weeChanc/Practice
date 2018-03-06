package com.example.weechan.myapplication.data

import com.example.weechan.myapplication.bean.ArticleDetial
import com.example.weechan.myapplication.data.local.ArticleLocalDataSource
import com.example.weechan.myapplication.data.remote.ArticleRemoteDataSource

/**
 * Created by steve on 18-3-4.
 */
open class ArticleRepository(val localDataSource: ArticleLocalDataSource,
                        val remoteDataSource: ArticleRemoteDataSource) : ArticleDataSource{
    override fun downMoreArticle(callback: ArticleLocalDataSource.LoadArticleCallback) {
        remoteDataSource.downMoreArticle(callback)
    }

    override fun saveArticle(article: ArticleDetial) {
        localDataSource.saveArticle(article)
        remoteDataSource.saveArticle(article)
    }

    override fun deleteArticle(article: ArticleDetial) {
        localDataSource.saveArticle(article)
        remoteDataSource.saveArticle(article)
    }

    override fun getArticles(count: Int, callback: ArticleLocalDataSource.LoadArticlesCallback) {
        localDataSource.getArticles(count,callback)
    }

    override fun deleteAllArticles() {
        localDataSource.deleteAllArticles()
        remoteDataSource.deleteAllArticles()
    }

}