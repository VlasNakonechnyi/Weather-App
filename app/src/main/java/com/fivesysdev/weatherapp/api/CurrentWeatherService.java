package com.fivesysdev.weatherapp.api;

import com.fivesysdev.weatherapp.model.dto.FullWeatherInfoDto;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrentWeatherService {

    String API_KEY_PARAM_NAME = "appid";
    String LATITUDE_PARAM_NAME = "lat";

    String LONGITUDE_PARAM_NAME = "lon";
    String EXCLUDE_PARAM_NAME = "exclude";



    @GET("onecall")
    Single<FullWeatherInfoDto> getCurrentWeatherInfo(
            @Query(API_KEY_PARAM_NAME) String apiKey,
            @Query(LATITUDE_PARAM_NAME) String lat,
            @Query(LONGITUDE_PARAM_NAME) String lon,
            @Query(EXCLUDE_PARAM_NAME) String exclude
    );
}
