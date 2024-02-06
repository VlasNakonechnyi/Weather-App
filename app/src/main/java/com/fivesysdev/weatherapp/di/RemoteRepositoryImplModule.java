package com.fivesysdev.weatherapp.di;

import com.fivesysdev.weatherapp.api.CurrentWeatherService;
import com.fivesysdev.weatherapp.repository.RemoteRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.disposables.Disposable;
@Module
@InstallIn(SingletonComponent.class)
public class RemoteRepositoryImplModule {
    @Provides
    @Singleton
    public RemoteRepositoryImpl provideRemoteRepository(CurrentWeatherService service, Disposable disposable) {
        return new RemoteRepositoryImpl(service, disposable);
    }
}
