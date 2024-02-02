package com.fivesysdev.weatherapp.di;

import android.app.Activity;

import com.fivesysdev.weatherapp.MainActivity;
import com.fivesysdev.weatherapp.api.CurrentWeatherService;
import com.fivesysdev.weatherapp.api.RetrofitClient;
import com.fivesysdev.weatherapp.contract.MainContract;
import com.fivesysdev.weatherapp.presenter.WeatherPresenter;
import com.fivesysdev.weatherapp.repository.RemoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public static CurrentWeatherService provideCurrentWeatherService() {
        return RetrofitClient.getInstance()
                .create(CurrentWeatherService.class);
    }
    @Provides
    public RemoteRepository provideRemoteRepository(CurrentWeatherService currentWeatherService) {
        return new RemoteRepository(currentWeatherService);
    }
    @Provides
    public WeatherPresenter provideWeatherPresenter(MainContract.View activity, RemoteRepository repository) {
        return new WeatherPresenter(activity, repository);
    }

}
