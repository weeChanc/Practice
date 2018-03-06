package com.example.weechan.myapplication.data.remote

import android.util.Log
import com.example.weechan.myapplication.bean.ArticleDetial
import com.example.weechan.myapplication.data.ArticleDataSource
import com.example.weechan.myapplication.data.local.ArticleLocalDataSource
import com.example.weechan.myapplication.network.RetrofitClient
import com.google.gson.Gson
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by steve on 18-3-4.
 */
object ArticleRemoteDataSource : ArticleDataSource {
    override fun getArticles(count: Int, callback: ArticleLocalDataSource.LoadArticlesCallback) {

    }

    override fun downMoreArticle(callback: ArticleLocalDataSource.LoadArticleCallback) {
        RetrofitClient.articleRetrofit.randomArticle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({callback.onTasksLoaded(it.data);Log.e("ArticleRemoteDataSource",it.data.content)},{callback.onDataNotAvailable()})
    }

    override fun saveArticle(article: ArticleDetial) {
    }

    override fun deleteArticle(article: ArticleDetial) {

    }

    override fun deleteAllArticles() {
    }

}