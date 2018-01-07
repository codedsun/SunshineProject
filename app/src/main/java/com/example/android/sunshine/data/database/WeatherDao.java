package com.example.android.sunshine.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.Date;

/**
 * Created by suneetsrivastava on 07/01/18.
 */

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(WeatherEntry ... weather);

    @Query("Select * from weather where date = :date")
    void getWeatherByDate(Date date);
}