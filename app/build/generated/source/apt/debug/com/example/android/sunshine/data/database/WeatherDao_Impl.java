package com.example.android.sunshine.data.database;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Date;

public class WeatherDao_Impl implements WeatherDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfWeatherEntry;

  public WeatherDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWeatherEntry = new EntityInsertionAdapter<WeatherEntry>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `weather`(`id`,`weatherIconId`,`date`,`min`,`max`,`humidity`,`pressure`,`wind`,`degrees`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WeatherEntry value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getWeatherIconId());
        final Long _tmp;
        _tmp = DateConverter.toTimestamp(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
        stmt.bindDouble(4, value.getMin());
        stmt.bindDouble(5, value.getMax());
        stmt.bindDouble(6, value.getHumidity());
        stmt.bindDouble(7, value.getPressure());
        stmt.bindDouble(8, value.getWind());
        stmt.bindDouble(9, value.getDegrees());
      }
    };
  }

  @Override
  public void bulkInsert(WeatherEntry... weather) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfWeatherEntry.insert(weather);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void getWeatherByDate(Date date) {
    final String _sql = "Select * from weather where date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final Long _tmp;
    _tmp = DateConverter.toTimestamp(date);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
