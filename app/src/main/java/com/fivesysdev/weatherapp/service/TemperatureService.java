package com.fivesysdev.weatherapp.service;

public class TemperatureService {
    private static final int MIN_LEN = 1;
    public static String fromKelvinToCelcius(String temperature) {
        double temp = Double.parseDouble(temperature);
        temp -= 273.15;
        temp = Math.round(temp);

        return Integer.toString((int)temp);
    }
}
