package com.fivesysdev.weatherapp.di;

import com.fivesysdev.weatherapp.api.CurrentWeatherService;
import com.fivesysdev.weatherapp.repository.RemoteRepository;
import com.fivesysdev.weatherapp.repository.RemoteRepositoryImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.disposables.Disposable;

@InstallIn(SingletonComponent.class)
@Module
public abstract class RemoteRepositoryModule {
    @Binds
    @Singleton
    abstract RemoteRepository bindDataSource(RemoteRepositoryImpl repository);

}
