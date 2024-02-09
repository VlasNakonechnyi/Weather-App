package com.fivesysdev.weatherapp.mapper;

import com.fivesysdev.weatherapp.model.dto.CurrentDto;
import com.fivesysdev.weatherapp.model.dto.FullWeatherInfoDto;
import com.fivesysdev.weatherapp.model.dto.HourlyDto;
import com.fivesysdev.weatherapp.model.dto.RainDto;
import com.fivesysdev.weatherapp.model.dto.SnowDto;
import com.fivesysdev.weatherapp.model.dto.WeatherDto;
import com.fivesysdev.weatherapp.model.dto.WeatherInfoDto;
import com.fivesysdev.weatherapp.model.local.Current;
import com.fivesysdev.weatherapp.model.local.FullWeatherInfo;
import com.fivesysdev.weatherapp.model.local.Hourly;
import com.fivesysdev.weatherapp.model.local.Rain;
import com.fivesysdev.weatherapp.model.local.Snow;
import com.fivesysdev.weatherapp.model.local.Weather;
import com.fivesysdev.weatherapp.model.local.WeatherInfo;

import java.util.ArrayList;
import java.util.List;

public class WeatherAppMapper {



    public static Current mapCurrentDtoToCurrent(CurrentDto dto) {
        return new Current(
                dto.getDt(),
                dto.getSunrise(),
                dto.getSunset(),
                dto.getTemp(),
                dto.getPressure(),
                dto.getHumidity(),
                dto.getUvi(),
                dto.getClouds(),
                dto.getWindSpeed(),
                dto.getWindDeg(),
                dto.getWindGust(),
                dto.getWeather()
        );
    }
    public static FullWeatherInfo mapFullWeatherInfoDtoToFullWeatherInfo(FullWeatherInfoDto dto) {
        return new FullWeatherInfo(
                dto.getLat(),
                dto.getLon(),
                WeatherAppMapper.mapCurrentDtoToCurrent(dto.getCurrent()),
                dto.getHourly()
        );
    }
    public static Hourly mapHourlyDtoToHourly(HourlyDto dto) {
        return new Hourly(
                dto.getDt(),
                dto.getTemp(),
                dto.getHumidity(),
                dto.getClouds(),
                dto.getWeather()
        );
    }
    public static List<Hourly> mapHourlyDtoListToHourlyList(List<HourlyDto> dtos) {
        List <Hourly> list = new ArrayList<>();
        for (HourlyDto el : dtos) {
            list.add(WeatherAppMapper.mapHourlyDtoToHourly(el));
        }
        return list;
    }
    public static Rain mapRainDtoToRain(RainDto dto) {
        return new Rain(dto.get1h());
    }
    public static Snow mapSnowDtoToSnow(SnowDto dto) {
        return new Snow(dto.get1h());
    }
    public static Weather mapWeatherDtoToWeather(WeatherDto dto) {
        return new Weather(
          dto.getMain(),
          dto.getDescription(),
          dto.getIcon()
        );
    }
    public static List<Weather> mapWeatherDtoListToWeatherList(List<WeatherDto>dtos) {
        List <Weather> list = new ArrayList<>();
        for (WeatherDto el : dtos) {
            list.add(WeatherAppMapper.mapWeatherDtoToWeather(el));
        }
        return list;
    }
    public static List<WeatherInfo> mapWeatherInfoDtoListToWeatherInfoList(List<WeatherInfoDto>dtos) {
        List <WeatherInfo> list = new ArrayList<>();
        for (WeatherInfoDto el : dtos) {
            list.add(WeatherAppMapper.mapWeatherInfoDtoToWeatherInfo(el));
        }
        return list;
    }

    public static WeatherInfo mapWeatherInfoDtoToWeatherInfo(WeatherInfoDto dto) {
        return new WeatherInfo(
                dto.getMain(),
                dto.getDescription(),
                dto.getIcon()
        );
    }
}
