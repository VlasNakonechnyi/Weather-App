package com.fivesysdev.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FullWeatherInfo {


    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("timezone_offset")
    @Expose
    private Integer timezoneOffset;
    @SerializedName("current")
    @Expose
    private Current current;

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

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "FullWeatherInfo{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", timezone='" + timezone + '\'' +
                ", timezoneOffset=" + timezoneOffset +
                ", current=" + current +
                '}';
    }
}
