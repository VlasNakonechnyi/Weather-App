package com.fivesysdev.weatherapp.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
@InstallIn(SingletonComponent.class)
@Module
public class DisposableModule {
    @Provides
    @Singleton
    public Disposable provideDisposable() {
        return new CompositeDisposable();
    }
}
