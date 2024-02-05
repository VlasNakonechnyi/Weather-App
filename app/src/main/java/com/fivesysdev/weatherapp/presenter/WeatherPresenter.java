package com.fivesysdev.weatherapp.presenter;



import android.content.Context;
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

import dagger.hilt.android.scopes.ActivityScoped;

@ActivityScoped
public class WeatherPresenter implements MainContract.Presenter {
    private Handler handler;

    public RemoteRepository repository;

    public MainContract.View view;
    @Inject
    public WeatherPresenter(MainContract.View view, RemoteRepository repository) {
        this.view = view;
        this.repository = repository;
        handler = new Handler();
        scheduleInternetCheck();
    }

    public void loadFullWeatherInfo() {
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
                (ConnectivityManager) ((MainActivity)view).getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }



}
