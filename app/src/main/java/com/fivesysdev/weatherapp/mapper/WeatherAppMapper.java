package com.fivesysdev.weatherapp.mapper;

import com.fivesysdev.weatherapp.model.dto.CurrentDto;
import com.fivesysdev.weatherapp.model.dto.FullWeatherInfoDto;
import com.fivesysdev.weatherapp.model.dto.HourlyDto;
import com.fivesysdev.weatherapp.model.local.Current;
import com.fivesysdev.weatherapp.model.local.FullWeatherInfo;
import com.fivesysdev.weatherapp.model.local.Hourly;

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
}
