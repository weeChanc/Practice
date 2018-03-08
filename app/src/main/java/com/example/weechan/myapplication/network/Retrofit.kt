package com.example.weechan.myapplication.network


import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by weechan on 18-3-4.
 */
object RetrofitClient {

    val okClient = OkHttpClient.Builder().addInterceptor { chain ->
        val req = chain.request()
        Log.e("RetrofitClient REQ : ",req?.url()?.url().toString())
        chain.proceed(req)

    }.build()

    val articleRetrofit by lazy {
        Retrofit.Builder().baseUrl("https://interface.meiriyiwen.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okClient)
                .build().create(ArticleApi::class.java)
    }

}