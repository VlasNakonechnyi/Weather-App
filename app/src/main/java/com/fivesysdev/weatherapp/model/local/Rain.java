package com.fivesysdev.weatherapp.model.local;

import com.google.gson.annotations.SerializedName;

public class Rain {
    private Double h;

    public Rain(Double h) {
        this.h = h;
    }

    public Double get1h() {
        return h;
    }

    public void set1h(Double h) {
        this.h = h;
    }
}
