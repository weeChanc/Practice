package com.example.weechan.myapplication.bean

/**
 * Created by weechan on 18-3-4.
 */
data class Article(val data: ArticleDetial)

data class ArticleDetial(val author: String,
                         val title: String,
                         val digest: String,
                         val content: String)