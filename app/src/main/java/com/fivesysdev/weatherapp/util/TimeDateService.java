package com.fivesysdev.weatherapp.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeDateService {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String unixTimeToHh_mm(Long time) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneId.systemDefault());

        // Format the LocalDateTime as hours and minutes
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return localDateTime.format(formatter);
    }
}
