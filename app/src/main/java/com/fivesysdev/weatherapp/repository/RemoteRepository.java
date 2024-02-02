package com.fivesysdev.weatherapp.repository;

import android.util.Log;

import com.fivesysdev.weatherapp.api.CurrentWeatherService;
import com.fivesysdev.weatherapp.api.RetrofitClient;
import com.fivesysdev.weatherapp.model.FullWeatherInfo;
import com.fivesysdev.weatherapp.model.Hourly;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class RemoteRepository {
    @Inject
    public CurrentWeatherService service;
    private final static String API_KEY = "272cf50ecf4de602c6de77fea0cb00ad";
    private final static String LATITUDE_PARAM = "49.23278";
    private final static String LONGITUDE_PARAM = "28.48097";
    private final static String EXCLUDE_PARAM = "daily,minutely,alerts";
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Inject
    public RemoteRepository(CurrentWeatherService service) {
        this.service = service;
    }

    public interface DataLoadedCallback {
        void onDataLoaded(FullWeatherInfo info);

        void onError(Throwable throwable);
    }


    public void loadFullWeatherInfo(DataLoadedCallback callback) {
        Log.d("ON_DATA_LOADED", "started");

        Disposable disposable = service
                .getCurrentWeatherInfo(API_KEY, LATITUDE_PARAM, LONGITUDE_PARAM, EXCLUDE_PARAM)
                .retry()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        callback::onDataLoaded,
                        callback::onError
                );
        Log.d("ON_DATA_LOADED", "ended");
        compositeDisposable.add(disposable);


    }
}
