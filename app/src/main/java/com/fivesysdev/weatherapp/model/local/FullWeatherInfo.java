package com.fivesysdev.weatherapp.model.local;

import com.fivesysdev.weatherapp.model.dto.HourlyDto;

import java.util.List;

public class FullWeatherInfo {

    private Double lat;

    private Double lon;

    private Current current;

    private List<HourlyDto> hourlyDto;

    public FullWeatherInfo(
            Double lat,
            Double lon,
            Current current,
            List<HourlyDto> hourlyDto
    ) {
        this.lat = lat;
        this.lon = lon;
        this.current = current;
        this.hourlyDto = hourlyDto;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }



    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public List<HourlyDto> getHourly() {
        return hourlyDto;
    }

    public void setHourly(List<HourlyDto> hourlyDto) {
        this.hourlyDto = hourlyDto;
    }
}
