package com.example.android.sqliteweather.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SavedLocationsDao {
    @Insert
    void insert(SavedLocation location);

    @Delete
    void delete(SavedLocation location);

    @Query("SELECT * FROM locations")
    LiveData<List<SavedLocation>> getAllLocations();

    @Query("SELECT * FROM locations WHERE name = :location LIMIT 1")
    SavedLocation getSavedLocationByName(String location);
}
