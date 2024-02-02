package com.fivesysdev.weatherapp.service;

public class IconService {
    private static final String BASE_URL = "https://openweathermap.org/img/wn/";
    private static final String POSTFIX = "@2x.png";
    public static String getIconUrl(String iconId) {
        return BASE_URL + iconId + POSTFIX;
    }
}
