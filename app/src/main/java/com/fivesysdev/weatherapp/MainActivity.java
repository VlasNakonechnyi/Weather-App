package com.fivesysdev.weatherapp;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fivesysdev.weatherapp.databinding.ActivityMainBinding;
import com.fivesysdev.weatherapp.model.FullWeatherInfo;
import com.fivesysdev.weatherapp.presenter.WeatherPresenter;
import com.fivesysdev.weatherapp.service.Direction;
import com.fivesysdev.weatherapp.service.SkyClarityService;
import com.fivesysdev.weatherapp.service.TemperatureService;
import com.fivesysdev.weatherapp.service.TimeDateService;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    WeatherPresenter presenter;
    Snackbar snackbar;
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
        snackbar = Snackbar.make(binding.getRoot(), "No Internet Connection", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", v -> presenter.loadFullWeatherInfo());
        presenter = new WeatherPresenter();
        presenter.attachView(this);
        presenter.loadFullWeatherInfo();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void displayWeatherInfo(FullWeatherInfo info) {
        Date date = new Date();
        SimpleDateFormat dateFor = new SimpleDateFormat("E, dd MMM, yyyy");
        String stringDate = dateFor.format(date);
        Double temperature = info.getCurrent().getTemp();
        String windInfo = String.valueOf(new StringBuilder("Wind: ")
                .append(info.getCurrent().getWindSpeed())
                .append("m/s, ").append(Direction.closestToDegrees(info.getCurrent().getWindDeg()).name()));

        String rainProb = "Humidity: " + info.getCurrent().getHumidity() + "%";
        String stringTime = TimeDateService.unixTimeToHh_mm((long)info.getCurrent().getDt());
        String clouds = SkyClarityService.convertToWeatherDescription(info.getCurrent().getClouds());



        binding.dateTextView.setText(stringDate);
        binding.temperatureTextView.setText(TemperatureService.fromKelvinToCelsius(temperature));
        binding.timeTextView.setText(stringTime);
        binding.windTextView.setText(windInfo);
        binding.rainProbabilityTextView.setText(rainProb);
        binding.skyClarityText.setText(clouds);
        binding.switch1.setOnCheckedChangeListener((buttonView, isChecked) -> presenter.temperatureSwitch(isChecked, temperature));

    }

    public void displayFahrenheitTemp(String temp) {
        binding.temperatureTextView.setText(temp);
    }
    public void displayCelsiusTemp(String temp) {
        binding.temperatureTextView.setText(temp);
    }
    public void showLoader() {
        binding.loadingLayout.loadingLayout.setVisibility(View.VISIBLE);
    }
    public void hideLoader() {
        binding.loadingLayout.loadingLayout.setVisibility(View.INVISIBLE);
    }
    public void showSnackBar() {
        snackbar.show();
    }
    public void hideSnackBar() {
        snackbar.dismiss();
    }
}