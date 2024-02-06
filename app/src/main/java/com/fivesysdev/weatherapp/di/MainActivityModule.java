package com.fivesysdev.weatherapp.di;

import android.app.Activity;

import com.fivesysdev.weatherapp.MainActivity;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@InstallIn(ActivityComponent.class)
@Module
public class MainActivityModule {
    @Provides
    MainActivity provideActivity(Activity activity)  {
        return (MainActivity) activity;
    }
}
