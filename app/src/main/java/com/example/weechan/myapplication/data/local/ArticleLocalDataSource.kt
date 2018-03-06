package com.example.weechan.myapplication.data.local

import com.example.weechan.myapplication.App
import com.example.weechan.myapplication.bean.ArticleDetial
import com.example.weechan.myapplication.data.ArticleDataSource
import com.example.weechan.myapplication.utils.async
import com.example.weechan.myapplication.utils.runOnMain
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by steve on 18-3-4.
 */
class ArticleLocalDataSource(private val dao: ArticleDao) : ArticleDataSource {
    override fun downMoreArticle(callback: LoadArticleCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    interface LoadArticlesCallback {
        fun onTasksLoaded(article: List<ArticleDetial>)
        fun onDataNotAvailable()
    }

    interface LoadArticleCallback {
        fun onTasksLoaded(article: ArticleDetial)
        fun onDataNotAvailable()
    }

    override fun saveArticle(article: ArticleDetial) {
        async { dao.insertArticle(article) }
    }

    override fun deleteArticle(article: ArticleDetial) {
        async { dao.deleteArticle(article) }
    }

    override fun deleteAllArticles() {
        async { dao.deleteAllArticles() }
    }

    override fun getArticles(count: Int, callback: LoadArticlesCallback) {
        async {
            val datas = dao.queryArticle();
            runOnMain { callback.onTasksLoaded(datas) }
        }
    }

}