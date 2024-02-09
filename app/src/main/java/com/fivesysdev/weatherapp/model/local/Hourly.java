package com.fivesysdev.weatherapp.model.local;

import com.fivesysdev.weatherapp.mapper.WeatherAppMapper;
import com.fivesysdev.weatherapp.model.dto.WeatherInfoDto;

import java.util.List;
import java.util.Objects;

public class Hourly {

    private Integer dt;

    private Double temp;

    private Integer humidity;
    private Integer clouds;

    private List<WeatherInfo> weather;

    public Hourly(Integer dt, Double temp, Integer humidity, Integer clouds, List<WeatherInfoDto> weather) {
        this.dt = dt;
        this.temp = temp;
        this.humidity = humidity;
        this.clouds = clouds;
        this.weather = WeatherAppMapper.mapWeatherInfoDtoListToWeatherInfoList(weather);
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hourly hourly = (Hourly) o;
        return Objects.equals(temp, hourly.temp) && Objects.equals(humidity, hourly.humidity) && Objects.equals(clouds, hourly.clouds) && Objects.equals(weather, hourly.weather);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temp, humidity, clouds, weather);
    }
}
