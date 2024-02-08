package com.fivesysdev.weatherapp.presenter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

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

    private final RemoteRepository repository;
    private final LocationManager locationManager;
    private Location locationByNetwork;
    private final MainContract.View view;
    private LocationListener networkLocationListener;
    private final static double LATITUDE_PARAM = 49.23278;
    private final static double LONGITUDE_PARAM = 28.48097;

    @Inject
    public WeatherPresenter(
            MainContract.View view,
            RemoteRepository repository,
            LocationManager locationManager
    ) {
        this.view = view;
        this.repository = repository;
        this.locationManager = locationManager;
        initNetworkLocationListener();
        handler = new Handler();

        scheduleInternetCheck();

    }


    @SuppressLint("MissingPermission")
    public void loadFullWeatherInfo() {

        Log.d("ON_DATA_LOADED", "loadFullWeatherInfo STARTED");

        try {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    100,
                    0F,
                    networkLocationListener
            );
            double latitude = LATITUDE_PARAM;
            double longitude = LONGITUDE_PARAM;


            locationByNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (locationByNetwork != null) {
                    latitude = locationByNetwork.getLatitude();
                    longitude = locationByNetwork.getLongitude();
            }
            view.showLoader();
            repository.loadFullWeatherInfo(new RemoteRepositoryImpl.DataLoadedCallback() {

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
            }, latitude, longitude);

        } catch (SecurityException exception) {
            view.showMissingPermissionsLayout();
        }
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
    private boolean isNetworkLocationAvailable() {
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) ((MainActivity) view).getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
    private void initNetworkLocationListener() {

        networkLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Log.d("LOCATION___", "WORKED");
                locationByNetwork = location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}

            @Override
            public void onProviderEnabled(@NonNull String provider) {}

            @Override
            public void onProviderDisabled(@NonNull String provider) {}
        };
    }


}
