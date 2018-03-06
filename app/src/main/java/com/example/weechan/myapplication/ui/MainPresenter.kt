package com.example.weechan.myapplication.ui

import com.example.weechan.myapplication.bean.ArticleDetial
import com.example.weechan.myapplication.data.ArticleRepository
import com.example.weechan.myapplication.data.local.ArticleLocalDataSource

/**
 * Created by steve on 18-3-4.
 */
class MainPresenter(val view : MainContract.View , val articleRepository : ArticleRepository) : MainContract.Presenter{

    init{
        view.setPresenter(this)
    }

    val articles : MutableList<ArticleDetial> by lazy { mutableListOf<ArticleDetial>() }

    override fun loadMoreArticle(count: Int) {
        articleRepository.localDataSource.getArticles(count,object : ArticleLocalDataSource.LoadArticlesCallback{
            override fun onTasksLoaded(article: List<ArticleDetial>) {
                articles.addAll(article)
                view.showArticles(articles)
            }
            override fun onDataNotAvailable() {
                view.showFailMessage()
            }
        })
    }

    override fun downMoreArticle(count: Int) {

        var mCount = count

        if(count == 0){
            view.showArticles(articles)
            view.stopLoading()
            return
        }

        articleRepository.remoteDataSource.downMoreArticle(object : ArticleLocalDataSource.LoadArticleCallback{
            override fun onTasksLoaded(article: ArticleDetial) {
                articles.add(0,article)
                articleRepository.localDataSource.saveArticle(article)
                downMoreArticle(--mCount)
            }

            override fun onDataNotAvailable() {
                downMoreArticle(count)
            }
        }

        )
    }

    override fun removeArticle(article: ArticleDetial) {
        articleRepository.localDataSource.deleteArticle(article)
    }



    override fun start() {
        loadMoreArticle(20)
    }



}