package com.example.weechan.myapplication.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.weechan.myapplication.bean.ArticleDetial
import io.reactivex.Observable
import io.reactivex.Observer

/**
 * Created by steve on 18-3-4.
 */

@Dao
interface ArticleDao {

    @Insert
    fun insertArticle(article : ArticleDetial)

    @Delete()
    fun deleteArticle(article:ArticleDetial)

    @Query("DELETE FROM article")
    fun deleteAllArticles()

    @Query("SELECT * FROM article LIMIT :count")
    fun queryArticle(count : Int):List<ArticleDetial>
}