package com.fivesysdev.weatherapp.model.dto;

import com.google.gson.annotations.SerializedName;

public class RainDto {

    @SerializedName("1h")

    private Double h;

    public Double get1h() {
        return h;
    }

    public void set1h(Double h) {
        this.h = h;
    }

}
