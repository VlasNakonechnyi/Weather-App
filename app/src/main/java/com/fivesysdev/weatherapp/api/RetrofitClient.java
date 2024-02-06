package com.fivesysdev.weatherapp.api;


import com.fivesysdev.weatherapp.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit instance;

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(BuildConfig.BASE_URL)
                    .build();
        }
        return instance;
    }
    public static CurrentWeatherService currentWeatherService = getInstance().create(CurrentWeatherService.class);
}
