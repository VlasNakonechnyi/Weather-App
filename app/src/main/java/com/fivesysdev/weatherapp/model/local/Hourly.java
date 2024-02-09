package com.fivesysdev.weatherapp.model.local;

import com.fivesysdev.weatherapp.model.dto.WeatherInfo;

import java.util.List;

public class Hourly {

    private Integer dt;

    private Double temp;

    private Integer humidity;
    private Integer clouds;

    private List<WeatherInfo> weather;

    public Hourly(Integer dt, Double temp, Integer humidity, Integer clouds, List<WeatherInfo> weather) {
        this.dt = dt;
        this.temp = temp;
        this.humidity = humidity;
        this.clouds = clouds;
        this.weather = weather;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }


    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }


    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }


    public List<WeatherInfo> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherInfo> weather) {
        this.weather = weather;
    }

}
