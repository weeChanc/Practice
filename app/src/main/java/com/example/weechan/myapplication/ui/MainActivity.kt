package com.example.weechan.myapplication.ui

import android.content.Intent
import android.graphics.Canvas
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemSwipeListener
import com.example.weechan.myapplication.App
import com.example.weechan.myapplication.R
import com.example.weechan.myapplication.bean.ArticleDetial
import com.example.weechan.myapplication.data.ArticleRepository
import com.example.weechan.myapplication.data.local.ArticleLocalDataSource
import com.example.weechan.myapplication.data.remote.ArticleRemoteDataSource
import com.example.weechan.myapplication.utils.toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity(), MainContract.View {
    override fun stopLoading() {
        refresh.finishLoadMore(100)
        refresh.finishRefresh(100)
    }

    private var mPresenter: MainContract.Presenter by Delegates.notNull()
    private var adapter: ArticleAdapter? = null

    override fun setPresenter(presenter: MainContract.Presenter) {
        mPresenter = presenter
    }

    override fun showArticles(articles: List<ArticleDetial>) {
        if (adapter == null) {
            adapter = ArticleAdapter(R.layout.item, articles)
            adapter?.openLoadAnimation()

            recyclerVivew.adapter = adapter
            recyclerVivew.layoutManager = LinearLayoutManager(this)
            adapter?.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                val intent = Intent(this,ArticleReader::class.java)
                with(articles[position]){
                    intent.putExtra("content",content)
                    intent.putExtra("author",author)
                }
               startActivity(intent)
            }
        }
        Log.e("MainActivity",articles.size.toString())
        adapter?.notifyDataSetChanged()
        recyclerVivew.smoothScrollToPosition(0)
    }


    override fun showFailMessage() {
        toast("fail")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainPresenter(this, ArticleRepository(ArticleLocalDataSource(App.db.articleDao()), ArticleRemoteDataSource))
        mPresenter.start()

        refresh.setOnRefreshListener {
            mPresenter.downMoreArticle(10)
        }




    }


}
