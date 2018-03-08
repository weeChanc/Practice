package com.example.weechan.myapplication.network;

import com.example.weechan.myapplication.bean.Article;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;


/**
 * Created by weechan on 18-3-4.
 */

public interface ArticleApi {

    @GET("article/random?dev=1")
    @Headers({"User-Agent:Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36",
    "Host:interface.meiriyiwen.com"})
    Observable<Article> getRandomArticle();
}
