package com.example.weechan.myapplication.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by weechan on 18-3-3.
 */

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    Flowable<List<User>> queryAll();

    @Query("SELECT * FROM user WHERE uuid=:id")
    User queryById(int id);

    @Query("SELECT * FROM user WHERE uuid IN (:userIds)")
    List<User> queryIn(int[] userIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Insert
    void insetAll(User ...users);
}
