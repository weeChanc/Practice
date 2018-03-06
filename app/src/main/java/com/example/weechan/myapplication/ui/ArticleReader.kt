package com.example.weechan.myapplication.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weechan.myapplication.R
import kotlinx.android.synthetic.main.activity_article_reader.*
import kotlinx.android.synthetic.main.activity_article_reader.view.*


class ArticleReader : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_reader)

        with(webView){
            getSettings().setJavaScriptEnabled(true);
            getSettings().setDefaultTextEncodingName("utf-8");
            val html = """
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${intent.getStringExtra("author")}</title>
</head>
<body>
    ${intent.getStringExtra("content")}
</body>
</html>
"""

            loadDataWithBaseURL(null,html,"text/html","UTF-8",null)
        }
    }
}
