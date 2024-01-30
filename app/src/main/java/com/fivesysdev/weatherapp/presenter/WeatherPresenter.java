package com.fivesysdev.weatherapp.presenter;

import android.util.Log;

import com.fivesysdev.weatherapp.model.FullWeatherInfo;
import com.fivesysdev.weatherapp.repository.RemoteRepository;

import java.util.Objects;

public class WeatherPresenter {
    private final RemoteRepository repository;
    public WeatherPresenter() {
        repository = new RemoteRepository();
    }
    public void loadFullWeatherInfo() {

        repository.loadFullWeatherInfo(new RemoteRepository.DataLoadedCallback() {

            @Override
            public void onDataLoaded(FullWeatherInfo info) {
                Log.d("ON_DATA_LOADED", info.toString());
            }

            @Override
            public void onError(Throwable throwable) {
                Log.d("ON_DATA_LOADED", throwable.getMessage());
            }
        });
    }
}
