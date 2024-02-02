package com.fivesysdev.weatherapp.di;

import com.fivesysdev.weatherapp.MainActivity;
import com.fivesysdev.weatherapp.contract.MainContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class WeatherViewModule {

    @Binds
    abstract MainContract.View provideView(MainActivity activity);
}
