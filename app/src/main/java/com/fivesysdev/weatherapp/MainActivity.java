package com.fivesysdev.weatherapp;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fivesysdev.weatherapp.databinding.ActivityMainBinding;
import com.fivesysdev.weatherapp.model.FullWeatherInfo;
import com.fivesysdev.weatherapp.presenter.WeatherPresenter;
import com.fivesysdev.weatherapp.service.TemperatureService;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        WeatherPresenter presenter = new WeatherPresenter();
        presenter.attachView(this);
        presenter.loadFullWeatherInfo();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void displayWeatherInfo(FullWeatherInfo info) {
        Date date = new Date();
        SimpleDateFormat dateFor = new SimpleDateFormat("E, dd MMM, yyyy");
        SimpleDateFormat timeFor = new SimpleDateFormat("hh:mm");
        String stringDate = dateFor.format(date);
        Instant instant = Instant.ofEpochSecond( info.getCurrent().getDt() );



        String stringTime = instant.toString();
        String temperature = info.getCurrent().getTemp().toString();
        String windInfo = new StringBuilder("Wind: ")
                .append(info.getCurrent().getWindSpeed())
                .append("m/s, ").append(info.getCurrent().getWindDeg()).toString();
        String rainProb = "Humidity: " + info.getCurrent().getHumidity() + "%";

        binding.dateTextView.setText(stringDate);
        binding.temperatureTextView.setText(TemperatureService.fromKelvinToCelcius(temperature));
        binding.timeTextView.setText(stringTime);
        binding.windTextView.setText(windInfo);
        binding.rainProbabilityTextView.setText(rainProb);

    }
}