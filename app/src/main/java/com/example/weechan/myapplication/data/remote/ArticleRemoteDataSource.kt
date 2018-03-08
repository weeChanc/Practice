package com.example.weechan.myapplication.data.remote

import android.util.Log
import com.example.weechan.myapplication.bean.ArticleDetial
import com.example.weechan.myapplication.data.ArticleDataSource
import com.example.weechan.myapplication.data.local.ArticleLocalDataSource
import com.example.weechan.myapplication.network.RetrofitClient
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by steve on 18-3-4.
 */
object ArticleRemoteDataSource : ArticleDataSource {

    override fun getArticles(count: Int): Flowable<List<ArticleDetial>> {
        return Flowable.just(mutableListOf())
    }

    override fun downMoreArticle(callback: ArticleLocalDataSource.LoadArticleCallback) {
        RetrofitClient.articleRetrofit.randomArticle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({callback.onTasksLoaded(it.data)},{callback.onDataNotAvailable()})
    }

    override fun saveArticle(article: ArticleDetial) {
    }

    override fun deleteArticle(article: ArticleDetial) {

    }

    override fun deleteAllArticles() {
    }

}