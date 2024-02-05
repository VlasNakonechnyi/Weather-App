package com.fivesysdev.weatherapp.repository;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public interface RemoteRepository {
    void loadFullWeatherInfo(RemoteRepositoryImpl.DataLoadedCallback callback);

}
