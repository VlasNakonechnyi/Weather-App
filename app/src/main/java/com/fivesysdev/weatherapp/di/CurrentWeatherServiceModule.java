package com.fivesysdev.weatherapp.di;

import com.fivesysdev.weatherapp.api.CurrentWeatherService;
import com.fivesysdev.weatherapp.api.RetrofitClient;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@InstallIn(ActivityComponent.class)
@Module
public class CurrentWeatherServiceModule {
    @Provides
    CurrentWeatherService provideCurrentWeatherService() {
        return RetrofitClient.getInstance().create(CurrentWeatherService.class);
    }
}
