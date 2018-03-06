package com.example.weechan.myapplication.ui

import com.example.weechan.myapplication.base.BasePresenter
import com.example.weechan.myapplication.base.BaseView
import com.example.weechan.myapplication.bean.ArticleDetial

/**
 * Created by steve on 18-3-4.
 */

interface MainContract{
    interface  View : BaseView<Presenter> {
        fun showArticles(articles : List<ArticleDetial>)
        fun showFailMessage()
        fun stopLoading()
    }

    interface Presenter : BasePresenter {
        fun loadMoreArticle(count : Int)
        fun downMoreArticle(count : Int)
        fun removeArticle(article:ArticleDetial)
    }
}
