package com.fivesysdev.weatherapp.di;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public class LocationModule {
    @Provides
    @Singleton
    public LocationManager provideLocation(Application application) {
        return (LocationManager) application.getSystemService(Context.LOCATION_SERVICE);
    }
}
