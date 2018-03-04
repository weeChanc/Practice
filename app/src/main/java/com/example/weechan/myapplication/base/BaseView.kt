package com.example.weechan.myapplication.base

/**
 * Created by steve on 18-3-4.
 */
interface BaseView<T> {
    fun setPresenter(presenter : T)
}