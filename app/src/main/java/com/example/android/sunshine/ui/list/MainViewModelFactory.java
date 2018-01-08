package com.example.android.sunshine.ui.list;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.android.sunshine.data.SunshineRepository;

/**
 * Created by suneetsrivastava on 08/01/18.
 */

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    public SunshineRepository sunshineRepository;
    public MainViewModelFactory(SunshineRepository sunshineRepository){
        this.sunshineRepository = sunshineRepository;

    }

    public <T extends ViewModel> T create(Class<T> modelClass){
        return (T) new MainActivityViewModel(sunshineRepository);
    }
}
