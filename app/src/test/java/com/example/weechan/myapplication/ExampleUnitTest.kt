package com.example.weechan.myapplication

import com.example.weechan.myapplication.bean.ArticleDetial
import com.example.weechan.myapplication.data.ArticleRepository
import com.example.weechan.myapplication.data.local.ArticleLocalDataSource
import com.example.weechan.myapplication.data.remote.ArticleRemoteDataSource
import com.example.weechan.myapplication.ui.MainContract
import com.example.weechan.myapplication.ui.MainPresenter
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Mock
    lateinit var view : MainContract.View


    lateinit var respostiroy : ArticleRepository

    lateinit var captureArticle : ArticleDetial

    @Captor
    lateinit var loadMoreCallback : ArgumentCaptor<ArticleLocalDataSource.LoadArticleCallback>

    lateinit var presenter : MainPresenter

    @Before
    fun setUp() {
        assertEquals(4, 2 + 2)
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view,respostiroy)
        respostiroy =  ArticleRepository(ArticleLocalDataSource(),ArticleRemoteDataSource)
//        assert(respostiroy.remoteDataSource != null)
    }


    @Test
    fun createPresenter(){
        presenter = MainPresenter(view,respostiroy)
        verify(view).setPresenter(presenter)

    }

    @Test
    fun loadTaskFromRepostoryAndLoadToView(){
        presenter.downMoreArticle(10)
        verify(respostiroy.remoteDataSource.downMoreArticle(loadMoreCallback.capture()))

    }

//    @Test
//    fun

//    @Test
//    fun test(){
//        presenter.downMoreArticle(10)
//        verify(resppostiroy.remoteDataSource.downMoreArticle(loadMoreCallback.capture()))
//        loadMoreCallback.value.onTasksLoaded(captureArticle)
//        print(captureArticle.toString())
//    }
}
