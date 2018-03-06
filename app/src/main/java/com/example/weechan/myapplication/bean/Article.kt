package com.example.weechan.myapplication.bean

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by weechan on 18-3-4.
 */
data class Article(val data: ArticleDetial)

@Entity(tableName = "article")
data class ArticleDetial(
        @PrimaryKey(autoGenerate = true)
        val id : Int,
        val author: String,
        val title: String,
        val digest: String,
        val content: String)