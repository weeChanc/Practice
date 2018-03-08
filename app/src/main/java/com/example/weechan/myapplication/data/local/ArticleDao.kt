package com.example.weechan.myapplication.data.local

import android.arch.persistence.room.*
import com.example.weechan.myapplication.bean.ArticleDetial
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer

/**
 * Created by steve on 18-3-4.
 */

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article : ArticleDetial)

    @Delete()
    fun deleteArticle(article:ArticleDetial)

    @Query("DELETE FROM article")
    fun deleteAllArticles()

    @Query("SELECT * FROM article ORDER BY id DESC")
    fun queryArticle(): Flowable<List<ArticleDetial>>

    @Query("SELECT * FROM article WHERE id BETWEEN :from AND :to")
    fun queryRange(from : Int , to : Int ):Flowable<List<ArticleDetial>>

    @Query("SELECT * FROM article WHERE id IN (:range)")
    fun queryRange(range:IntArray):Flowable<List<ArticleDetial>>
}