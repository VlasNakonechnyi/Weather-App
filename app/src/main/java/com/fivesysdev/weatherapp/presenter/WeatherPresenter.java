package com.fivesysdev.weatherapp.presenter;


import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.fivesysdev.weatherapp.MainActivity;
import com.fivesysdev.weatherapp.contract.MainContract;
import com.fivesysdev.weatherapp.model.FullWeatherInfo;
import com.fivesysdev.weatherapp.repository.RemoteRepository;
import com.fivesysdev.weatherapp.repository.RemoteRepositoryImpl;
import com.fivesysdev.weatherapp.service.TemperatureService;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.scopes.ActivityScoped;

@ActivityScoped
public class WeatherPresenter implements MainContract.Presenter {
    private final Handler handler;

    private final RemoteRepository repository;
    private final LocationManager locationManager;
    private Location locationByNetwork;
    private final MainContract.View view;
    private LocationListener networkLocationListener;

    @Inject
    public WeatherPresenter(
            MainContract.View view,
            RemoteRepository repository,
            LocationManager locationManager
    ) {
        this.view = view;
        this.repository = repository;
        this.locationManager = locationManager;

        handler = new Handler();

        scheduleInternetCheck();

    }


    @SuppressLint("MissingPermission")
    public void loadFullWeatherInfo() {
        Log.d("ON_DATA_LOADED", "loadFullWeatherInfo STARTED");


        view.showLoader();
        repository.loadFullWeatherInfo(new RemoteRepositoryImpl.DataLoadedCallback() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataLoaded(FullWeatherInfo info) {
                Log.d("ON_DATA_LOADED", info.toString());
                view.hideLoader();
                view.hideSnackBar();
                view.displayWeatherInfo(info);
            }

            @Override
            public void onError(Throwable throwable) {
                view.hideLoader();
                view.showSnackBar();
            }
        });


        Log.d("ON_DATA_LOADED", "loadFullWeatherInfo ENDED");
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
        }, 10000);
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
                (ConnectivityManager) ((MainActivity) view).getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


}
