package com.example.weechan.myapplication.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.arch.persistence.room.Room
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.weechan.myapplication.R
import com.example.weechan.myapplication.bean.ArticleDetial
import com.example.weechan.myapplication.db.AppDatabase
import com.example.weechan.myapplication.db.User
import com.example.weechan.myapplication.network.RetrofitClient
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
//import com.example.weechan.myapplication.network.RetrofitClient
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        thread {
//            val db = Room.databaseBuilder(applicationContext,
//                    AppDatabase::class.java, "user").build()
//            val dao = db.userdao();
//            for (i in 0..1000) {
//                dao.insert(User(i, "$i"))
//            }
//            dao.queryIn(IntArray(10){it*2}).forEach { Log.e("MainActivity",it.name) }
//            dao.queryAll().forEach { it.forEach{Log.e("MainActivity",it.name)} }
//            dao.queryAll().subscribeOn(Schedulers.io()).subscribe({Log.e("MainActividddty",it.name)},{it.printStackTrace()})
        }

        val data = mutableListOf<ArticleDetial>()

        val adapter = ArticleAdapter(R.layout.item,data)
        adapter.openLoadAnimation();
        recyclerVivew.adapter= adapter
        recyclerVivew.layoutManager = LinearLayoutManager(this  )

        refresh.setOnRefreshListener{

            RetrofitClient.articleRetrofit.getRandomArticle("https://interface.meiriyiwen.com/article/random?dev=1")
                    .subscribeOn(rx.schedulers.Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe ({ data.add(it.data) ; adapter.notifyDataSetChanged() },{e-> Log.e("MainActivity",e.toString())})
        }

    }
}
