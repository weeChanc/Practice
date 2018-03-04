package com.example.weechan.myapplication.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by weechan on 18-3-3.
 */

@Database(entities = {User.class},version=2)
public abstract class AppDatabase extends RoomDatabase{
    public abstract UserDao userdao();
}
