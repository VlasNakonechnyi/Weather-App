package com.fivesysdev.weatherapp.service;

public class SkyClarityService {
    public static String convertToWeatherDescription(int cloudCoverPercentage) {
        if (cloudCoverPercentage <= 20) {
            return "clear";
        } else if (cloudCoverPercentage <= 60) {
            return "partly cloudy";
        } else if (cloudCoverPercentage <= 80){
            return "mostly cloudy";
        } else return "cloudy";
    }
}
