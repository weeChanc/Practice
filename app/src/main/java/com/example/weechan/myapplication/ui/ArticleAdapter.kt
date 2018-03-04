package com.example.weechan.myapplication.ui

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.weechan.myapplication.R
import com.example.weechan.myapplication.bean.ArticleDetial

/**
 * Created by weechan on 18-3-4.
 */
class ArticleAdapter(layoutId : Int , data : MutableList<ArticleDetial>) : BaseQuickAdapter<ArticleDetial, BaseViewHolder>(layoutId,data) {
    override fun convert(helper: BaseViewHolder?, item: ArticleDetial?) {
        with(helper!!){
            setText(R.id.msg,"${item?.author}:${item?.title}")
        }
    }

}