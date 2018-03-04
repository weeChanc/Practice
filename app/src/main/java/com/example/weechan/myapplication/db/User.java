package com.example.weechan.myapplication.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by weechan on 18-3-3.
 */

@Entity(tableName = "user" )
public class User {
    @PrimaryKey
    private int uuid;
    @ColumnInfo(name = "name")
    private String name;

    public User(int uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
