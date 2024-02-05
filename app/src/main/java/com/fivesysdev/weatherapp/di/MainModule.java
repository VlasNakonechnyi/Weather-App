package com.fivesysdev.weatherapp.di;

import com.fivesysdev.weatherapp.MainActivity;
import com.fivesysdev.weatherapp.contract.MainContract;
import com.fivesysdev.weatherapp.presenter.WeatherPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@InstallIn(ActivityComponent.class)
@Module
abstract class MainModule {

    @Binds
    abstract MainContract.View bindActivity(MainActivity activity);

    @Binds
    abstract MainContract.Presenter bindPresenter(WeatherPresenter impl);

}
