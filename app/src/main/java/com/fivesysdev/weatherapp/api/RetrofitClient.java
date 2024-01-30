package com.fivesysdev.weatherapp.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static Retrofit instance;

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(BASE_URL)
                    .build();
        }
        return instance;
    }
    public static CurrentWeatherService currentWeatherService = getInstance().create(CurrentWeatherService.class);
}
