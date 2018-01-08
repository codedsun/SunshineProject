package com.example.android.sunshine.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.sunshine.data.SunshineRepository;
import com.example.android.sunshine.data.database.WeatherEntry;

import java.util.List;

/**
 * Created by suneetsrivastava on 08/01/18.
 */

public class MainActivityViewModel extends ViewModel {

    private SunshineRepository sunshineRepository;
    private LiveData<List<WeatherEntry>> mForecast;
    public MainActivityViewModel(SunshineRepository sunshineRepository){
        this.sunshineRepository = sunshineRepository;
        mForecast = sunshineRepository.getCurrentWeatherForecast();

    }

    public LiveData<List<WeatherEntry>> getForecast(){
        return mForecast;
    }


}
