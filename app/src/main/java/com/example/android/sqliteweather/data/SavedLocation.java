package com.example.android.sqliteweather.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "locations")
public class SavedLocation {
    @NonNull
    @PrimaryKey
    public String name;
}
