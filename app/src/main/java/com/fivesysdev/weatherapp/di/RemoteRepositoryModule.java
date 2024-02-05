package com.fivesysdev.weatherapp.di;

import com.fivesysdev.weatherapp.repository.RemoteRepository;
import com.fivesysdev.weatherapp.repository.RemoteRepositoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@InstallIn(ActivityComponent.class)
@Module
public abstract class RemoteRepositoryModule {
    @Binds
    abstract RemoteRepository bindDataSource(RemoteRepositoryImpl repository);
}
