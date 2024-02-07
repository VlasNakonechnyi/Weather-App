package com.fivesysdev.weatherapp.contract;

import com.fivesysdev.weatherapp.model.FullWeatherInfo;

public interface MainContract {
    interface View {
        void displayWeatherInfo(FullWeatherInfo info);
        void displayFahrenheitTemp(String temp);
        void displayCelsiusTemp(String temp);
        void showLoader();
        void hideLoader();
        void showSnackBar();
        void hideSnackBar();

        void showMissingPermissionsLayout();
    }

    interface Presenter {
        void loadFullWeatherInfo();
        void temperatureSwitch(boolean isChecked, Double temperature);


    }

}
