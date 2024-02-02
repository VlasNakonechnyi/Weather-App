package com.fivesysdev.weatherapp.service;

public class TemperatureService {
    private static final int MIN_LEN = 1;
    private static final String CELSIUS_SYM = "°C";
    private static final String FAHRENHEIT_SYM = "°F";

    public static String fromKelvinToCelsius(Double temperature) {
        double temp = temperature;
        temp -= 273.15;
        temp = Math.round(temp);

        return Integer.toString((int)temp);
    }

    public static String fromKelvinToFahrenheit(Double temperature) {
        return ((int)((temperature - 273.15)* 1.8 + 32)) + "";
    }
    public static String getCelsiusSym() {
        return CELSIUS_SYM;
    }
    public static String getFahrenheitSym() {
        return FAHRENHEIT_SYM;
    }

}
