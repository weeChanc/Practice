package com.example.weechan.myapplication.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.weechan.myapplication.bean.ArticleDetial;
import com.example.weechan.myapplication.data.local.ArticleDao;

/**
 * Created by weechan on 18-3-3.
 */

@Database(entities = {ArticleDetial.class},version=2)
public abstract class AppDatabase extends RoomDatabase{
    public abstract ArticleDao articleDao();
}
