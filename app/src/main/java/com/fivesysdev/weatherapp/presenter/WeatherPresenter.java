package com.fivesysdev.weatherapp.presenter;



import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.fivesysdev.weatherapp.MainActivity;
import com.fivesysdev.weatherapp.model.FullWeatherInfo;
import com.fivesysdev.weatherapp.repository.RemoteRepository;
import com.fivesysdev.weatherapp.service.TemperatureService;

public class WeatherPresenter {
    private Handler handler;
    private final RemoteRepository repository;
    private MainActivity view;
    public WeatherPresenter() {
        repository = new RemoteRepository();
        handler = new Handler();
        scheduleInternetCheck();
    }
    public void attachView(MainActivity view) {
        this.view = view;
    }
    public void loadFullWeatherInfo() {
    view.showLoader();
        repository.loadFullWeatherInfo(new RemoteRepository.DataLoadedCallback() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataLoaded(FullWeatherInfo info) {
                view.hideLoader();
                view.hideSnackBar();
                view.displayWeatherInfo(info);
            }

            @Override
            public void onError(Throwable throwable) {
                view.hideLoader();
                view.showSnackBar();
                Log.d("ON_DATA_LOADED", throwable.getMessage());
            }
        });
    }

    public void temperatureSwitch(boolean isChecked, Double temperature) {
        if (isChecked) {
            view.displayFahrenheitTemp(TemperatureService.fromKelvinToFahrenheit(temperature));
        } else {
            view.displayCelsiusTemp(TemperatureService.fromKelvinToCelsius(temperature));
        }
    }
    private void scheduleInternetCheck() {
        handler.postDelayed(() -> {
            checkInternetConnection();
            scheduleInternetCheck();
        }, 5000);
    }

    private void checkInternetConnection() {
        if (isInternetAvailable()) {

            view.hideSnackBar();
        } else {

            view.showSnackBar();
        }
    }

    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) view.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }



}
