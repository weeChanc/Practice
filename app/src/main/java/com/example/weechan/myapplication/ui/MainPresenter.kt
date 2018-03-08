package com.example.weechan.myapplication.ui

import android.util.Log
import com.example.weechan.myapplication.bean.ArticleDetial
import com.example.weechan.myapplication.data.ArticleRepository
import com.example.weechan.myapplication.data.local.ArticleLocalDataSource
import com.example.weechan.myapplication.utils.Fetcher
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by steve on 18-3-4.
 */
class MainPresenter(val view: MainContract.View, val articleRepository: ArticleRepository) : MainContract.Presenter {

    init {
        view.setPresenter(this)
    }

    val articles: MutableList<ArticleDetial> by lazy { mutableListOf<ArticleDetial>() }

    override fun loadMoreArticle(count: Int) {
        articleRepository.getArticles(count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { Log.e("MainPresenter","TOUCH");view.showArticles(articles)})
    }

    override fun downMoreArticle(count: Int) {

        var mCount = count

        if (count == 0) {
            view.showArticles(articles)
            view.stopLoading()
            return
        }

        articleRepository.downMoreArticle(object : ArticleLocalDataSource.LoadArticleCallback {
            override fun onTasksLoaded(article: ArticleDetial) {
                articles.add(0, article)
                articleRepository.saveArticle(article)
                downMoreArticle(mCount-1)
            }

            override fun onDataNotAvailable() {
                downMoreArticle(count)
            }
    })
    }

    override fun removeArticle(article: ArticleDetial) {
        articleRepository.deleteArticle(article)
    }


    override fun start() {
        loadMoreArticle(20)
    }


}