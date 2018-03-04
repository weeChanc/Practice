package com.example.weechan.myapplication.ui

import com.example.weechan.myapplication.bean.ArticleDetial
import com.example.weechan.myapplication.data.ArticleRepository
import com.example.weechan.myapplication.data.local.ArticleLocalDataSource

/**
 * Created by steve on 18-3-4.
 */
class MainPresenter(val view : MainContract.View , val articleRepository: ArticleRepository) : MainContract.Presenter{

    init{
        view.setPresenter(this)
    }

    val articles : MutableList<ArticleDetial> by lazy { mutableListOf<ArticleDetial>() }

    override fun start() {
        articleRepository.localDataSource.getArticles(10,object : ArticleLocalDataSource.LoadArticlesCallback{
            override fun onTasksLoaded(article: List<ArticleDetial>) {
                articles.addAll(article)
                view.showArticles(articles)
            }

            override fun onDataNotAvailable() {
                view.showFailMessage()
            }

        })


    }

    override fun loadMoreArticle() {
        articleRepository.remoteDataSource.loadMoreArticles(object : ArticleLocalDataSource.LoadArticleCallback{
            override fun onTasksLoaded(article: ArticleDetial) {
                articles.add(article)
                articleRepository.localDataSource.saveArticle(article)
                view.showArticles(articles)
                view.stopLoading()

            }

            override fun onDataNotAvailable() {
                view.showFailMessage()
                view.stopLoading()
            }
        }

        )
    }

}