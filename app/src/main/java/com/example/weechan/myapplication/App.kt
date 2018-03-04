package com.example.weechan.myapplication

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.example.weechan.myapplication.db.AppDatabase
import com.example.weechan.myapplication.utils.AppExecutors
//import com.mobile.utils.Utils
import kotlin.properties.Delegates

/**
 * Created by weechan on 18-3-4.
 */
class App : Application(){

    companion object {
        var ctx : App by Delegates.notNull()
        var executors : AppExecutors = AppExecutors()
        var db : AppDatabase by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        ctx = this
        db = Room.databaseBuilder(ctx, AppDatabase::class.java, "articles").build()
//        Utils.init(this)
    }
}