package com.example.weechan.myapplication.data

import com.example.weechan.myapplication.bean.ArticleDetial
import com.example.weechan.myapplication.data.local.ArticleLocalDataSource
import com.example.weechan.myapplication.data.remote.ArticleRemoteDataSource
import io.reactivex.Flowable

/**
 * Created by steve on 18-3-4.
 */
open class ArticleRepository(private val localDataSource: ArticleLocalDataSource,
                        private val remoteDataSource: ArticleRemoteDataSource) : ArticleDataSource{
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

    override fun getArticles(count: Int): Flowable<List<ArticleDetial>> {
        return localDataSource.getArticles(count)
    }

    override fun deleteAllArticles() {
        localDataSource.deleteAllArticles()
        remoteDataSource.deleteAllArticles()
    }

}