package com.fivesysdev.weatherapp.model.local;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class WeatherInfo {
    private String main;

    private String description;

    private String icon;

    public WeatherInfo(String main, String description, String icon) {
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherInfo that = (WeatherInfo) o;
        return Objects.equals(main, that.main) && Objects.equals(description, that.description) && Objects.equals(icon, that.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(main, description, icon);
    }
}
