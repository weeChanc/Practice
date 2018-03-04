package com.example.weechan.myapplication

import android.app.Application
import android.content.Context
//import com.mobile.utils.Utils
import kotlin.properties.Delegates

/**
 * Created by weechan on 18-3-4.
 */
class App : Application(){

    companion object {
        var ctx : App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        ctx = this

//        Utils.init(this)
    }
}