package com.example.weechan.myapplication.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.weechan.myapplication.App
import com.example.weechan.myapplication.R
import com.example.weechan.myapplication.bean.ArticleDetial
import com.example.weechan.myapplication.data.ArticleRepository
import com.example.weechan.myapplication.data.local.ArticleLocalDataSource
import com.example.weechan.myapplication.data.remote.ArticleRemoteDataSource
//import com.example.weechan.myapplication.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() ,MainContract.View {
    override fun stopLoading() {
        refresh.finishRefresh()
    }

    var mPresenter : MainContract.Presenter by Delegates.notNull()
    var adapter : ArticleAdapter? = null
   
    override fun setPresenter(presenter: MainContract.Presenter) {
        mPresenter = presenter
    }

    override fun showArticles(articles: List<ArticleDetial>) {
        if(adapter == null) {
            adapter = ArticleAdapter(R.layout.item,articles)
            adapter?.openLoadAnimation()
            recyclerVivew.adapter= adapter
            recyclerVivew.layoutManager = LinearLayoutManager(this  )
        }
        adapter?.notifyDataSetChanged()
    }

    override fun showFailMessage() {
        Log.e("MainActivity","FAIL")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        MainPresenter(this, ArticleRepository(ArticleLocalDataSource(App.db.articleDao()),ArticleRemoteDataSource))
        mPresenter.start()

        refresh.setOnRefreshListener{
            mPresenter.loadMoreArticle()

        }

    }
}
