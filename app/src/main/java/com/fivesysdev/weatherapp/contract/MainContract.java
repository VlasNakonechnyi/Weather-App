package com.fivesysdev.weatherapp.contract;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.fivesysdev.weatherapp.R;
import com.fivesysdev.weatherapp.model.FullWeatherInfo;
import com.fivesysdev.weatherapp.model.Hourly;
import com.fivesysdev.weatherapp.model.Weather;
import com.fivesysdev.weatherapp.repository.RemoteRepository;
import com.fivesysdev.weatherapp.service.Direction;
import com.fivesysdev.weatherapp.service.IconService;
import com.fivesysdev.weatherapp.service.TemperatureService;
import com.fivesysdev.weatherapp.service.TimeDateService;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface MainContract {
    interface View {
        void displayWeatherInfo(FullWeatherInfo info);
        void displayFahrenheitTemp(String temp);
        void displayCelsiusTemp(String temp);
        void showLoader();
        void hideLoader();
        void showSnackBar();
        void hideSnackBar();
    }

    interface Presenter {
        void loadFullWeatherInfo();
        void temperatureSwitch(boolean isChecked, Double temperature);
    }
}
