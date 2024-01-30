package com.fivesysdev.weatherapp.presenter;

import android.util.Log;

import com.fivesysdev.weatherapp.MainActivity;
import com.fivesysdev.weatherapp.model.FullWeatherInfo;
import com.fivesysdev.weatherapp.repository.RemoteRepository;

import java.util.Objects;

public class WeatherPresenter {
    private final RemoteRepository repository;
    private MainActivity view;
    public WeatherPresenter() {
        repository = new RemoteRepository();
    }
    public void attachView(MainActivity view) {
        this.view = view;
    }
    public void loadFullWeatherInfo() {

        repository.loadFullWeatherInfo(new RemoteRepository.DataLoadedCallback() {

            @Override
            public void onDataLoaded(FullWeatherInfo info) {
                view.displayWeatherInfo(info);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.d("ON_DATA_LOADED", throwable.getMessage());
            }
        });
    }
}
