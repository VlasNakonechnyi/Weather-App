package com.fivesysdev.weatherapp.repository;

public interface RemoteRepository {
    void loadFullWeatherInfo(RemoteRepositoryImpl.DataLoadedCallback callback, double latitude, double longitude);

}
