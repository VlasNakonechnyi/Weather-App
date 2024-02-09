package com.fivesysdev.weatherapp.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FullWeatherInfoDto {
    @SerializedName("lat")

    private Double lat;
    @SerializedName("lon")

    private Double lon;
    @SerializedName("timezone")

    private String timezone;
    @SerializedName("timezone_offset")

    private Integer timezoneOffset;
    @SerializedName("current")

    private CurrentDto currentDto;
    @SerializedName("hourly")

    private List<HourlyDto> hourlyDto;

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

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getTimezoneOffset() {
        return timezoneOffset;
    }

    public void setTimezoneOffset(Integer timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    public CurrentDto getCurrent() {
        return currentDto;
    }

    public void setCurrent(CurrentDto currentDto) {
        this.currentDto = currentDto;
    }

    public List<HourlyDto> getHourly() {
        return hourlyDto;
    }

    public void setHourly(List<HourlyDto> hourlyDto) {
        this.hourlyDto = hourlyDto;
    }

}
