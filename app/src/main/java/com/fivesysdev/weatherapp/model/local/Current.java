package com.fivesysdev.weatherapp.model.local;

import com.fivesysdev.weatherapp.model.dto.Weather;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Current {

    private Integer dt;

    private Integer sunrise;

    private Integer sunset;

    private Double temp;


    private Integer pressure;

    private Integer humidity;


    private Double uvi;

    private Integer clouds;


    private Double windSpeed;

    private Integer windDeg;

    private Double windGust;

    private List<Weather> weather;

    public Current(
            Integer dt,
            Integer sunrise,
            Integer sunset,
            Double temp,
            Integer pressure,
            Integer humidity,
            Double uvi,
            Integer clouds,
            Double windSpeed,
            Integer windDeg,
            Double windGust,
            List<Weather> weather
    ) {
        this.dt = dt;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.uvi = uvi;
        this.clouds = clouds;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
        this.windGust = windGust;
        this.weather = weather;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }


    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }


    public Double getUvi() {
        return uvi;
    }

    public void setUvi(Double uvi) {
        this.uvi = uvi;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }


    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(Integer windDeg) {
        this.windDeg = windDeg;
    }

    public Double getWindGust() {
        return windGust;
    }

    public void setWindGust(Double windGust) {
        this.windGust = windGust;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
