package com.fivesysdev.weatherapp.presenter;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.fivesysdev.weatherapp.MainActivity;
import com.fivesysdev.weatherapp.contract.MainContract;
import com.fivesysdev.weatherapp.model.FullWeatherInfo;
import com.fivesysdev.weatherapp.repository.RemoteRepository;
import com.fivesysdev.weatherapp.repository.RemoteRepositoryImpl;
import com.fivesysdev.weatherapp.service.TemperatureService;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ActivityScoped;

@ActivityScoped
public class WeatherPresenter implements MainContract.Presenter {
    private final Handler handler;
    private boolean isLocationPermitted;



    private final RemoteRepository repository;
    private final LocationManager locationManager;
    private Location locationByNetwork;
    private final MainContract.View view;
    private LocationListener networkLocationListener;
    private final Application application;

    @Inject
    public WeatherPresenter(
            MainContract.View view,
            RemoteRepository repository,
            LocationManager locationManager,
            Application application
    ) {
        this.view = view;
        this.repository = repository;
        this.locationManager = locationManager;
        this.application = application;
        handler = new Handler();
        initLocationListener();
        scheduleInternetCheck();

    }


    public void loadFullWeatherInfo() {
        if (isInternetAvailable()) {

            if (ActivityCompat.checkSelfPermission(application.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(application.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    5000,
                    0F,
                    networkLocationListener
            );
        }
    view.showLoader();
        locationByNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
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
        }, locationByNetwork.getLatitude(), locationByNetwork.getLongitude());
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
                (ConnectivityManager) ((MainActivity)view).getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void initLocationListener() {
        networkLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                locationByNetwork = location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
            }
        };

    }

}
