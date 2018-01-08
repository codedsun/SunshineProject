package com.example.android.sunshine.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by suneetsrivastava on 07/01/18.
 */

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(WeatherEntry ... weather);

    @Query("Select * from weather where date = :date")
    LiveData<WeatherEntry> getWeatherByDate(Date date);

    @Query("Select COUNT(date) from weather where date >= :date")
    int countAllFurtherWeather(Date date);

    @Query("Delete from weather where date < :date")
    void deleteOldData(Date date);

    @Query("Select * from weather where date >= :date")
    LiveData<List<WeatherEntry>> getCurrentWeatherForecast(Date date);


}
