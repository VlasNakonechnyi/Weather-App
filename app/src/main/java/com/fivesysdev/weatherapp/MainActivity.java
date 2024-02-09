package com.fivesysdev.weatherapp;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fivesysdev.weatherapp.adapters.WeatherAppAdapter;
import com.fivesysdev.weatherapp.contract.MainContract;
import com.fivesysdev.weatherapp.databinding.ActivityMainBinding;
import com.fivesysdev.weatherapp.mapper.WeatherAppMapper;
import com.fivesysdev.weatherapp.model.dto.FullWeatherInfoDto;
import com.fivesysdev.weatherapp.model.dto.HourlyDto;
import com.fivesysdev.weatherapp.model.dto.Weather;
import com.fivesysdev.weatherapp.model.local.Current;
import com.fivesysdev.weatherapp.model.local.FullWeatherInfo;
import com.fivesysdev.weatherapp.model.local.Hourly;
import com.fivesysdev.weatherapp.service.Direction;
import com.fivesysdev.weatherapp.service.IconService;
import com.fivesysdev.weatherapp.service.TemperatureService;
import com.fivesysdev.weatherapp.service.TimeDateService;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.disposables.Disposable;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements MainContract.View {
    private static final int REQUEST_CODE = 1;
    private static final String EMPTY_STRING = "";
    private ActivityMainBinding binding;
    @Inject
    MainContract.Presenter presenter;
    @Inject
    Disposable disposable;
    WeatherAppAdapter appAdapter;
    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        appAdapter = new WeatherAppAdapter();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        init();
        CheckPermissionGranted();
    }
    private void CheckPermissionGranted() {
        if (isLocationPermissionGranted()) {
            presenter.loadFullWeatherInfo();
        } else {
            requestPermission();
        }
    }
    private boolean isLocationPermissionGranted() {
        return ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                },
                REQUEST_CODE
        );
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (isLocationPermissionGranted()) {
                presenter.loadFullWeatherInfo();
            } else {
                showMissingPermissionsLayout();
            }
        }
    }

    public void init() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        snackbar = Snackbar.make(binding.getRoot(), "No Internet Connection", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", v -> presenter.loadFullWeatherInfo());

        binding.swipeRefresh.setOnRefreshListener(() -> presenter.loadFullWeatherInfo());
        binding.hourlyRecyclerView.setAdapter(appAdapter);
        binding.hourlyRecyclerView.setHasFixedSize(true);
    }

    public void displayWeatherInfo(FullWeatherInfoDto infoDto) {
        FullWeatherInfo info = WeatherAppMapper.mapFullWeatherInfoDtoToFullWeatherInfo(infoDto);
        List<HourlyDto> list = info.getHourly();

        System.out.println(list);
        appAdapter.setHourlyList(WeatherAppMapper.mapHourlyDtoListToHourlyList(list));


        Date date = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFor = new SimpleDateFormat("E, dd MMM, yyyy");
        String stringDate = dateFor.format(date);


        Current current = info.getCurrent();
        Double temperature = current.getTemp();
        String windInfo = getString(R.string.wind, current.getWindSpeed(), Direction.closestToDegrees(current.getWindDeg()).name());
        String rainProb = getString(R.string.humidity, info.getCurrent().getHumidity());
        String stringTime = TimeDateService.unixTimeToHh_mm((long) info.getCurrent().getDt());

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());


        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(info.getLat(), info.getLon(), 1);
        } catch (IOException e) {
            Log.d("Geocoder", "failed to parse location");
        }

        String locationString = EMPTY_STRING;
        if (addresses != null) {
            locationString = String.format("%s, %s",addresses.get(0).getLocality(), addresses.get(0).getCountryName());
        }


        Weather weather = info.getCurrent().getWeather().get(0);
        String clouds = weather.getDescription();
        binding.dateTextView.setText(stringDate);
        binding.temperatureTextView.setText(TemperatureService.fromKelvinToCelsius(temperature));
        binding.temperatureSymbolTextView.setText(TemperatureService.getCelsiusSym());

        binding.timeTextView.setText(stringTime);
        binding.windTextView.setText(windInfo);
        binding.rainProbabilityTextView.setText(rainProb);
        binding.skyClarityText.setText(clouds);
        binding.switch1.setOnCheckedChangeListener((buttonView, isChecked) -> presenter.temperatureSwitch(isChecked, temperature));
        binding.locationTextView.setText(locationString);
        Picasso.get().load(IconService.getIconUrl(weather.getIcon())).into(binding.imageView);
        Picasso.get().load(IconService.getIconUrl(weather.getIcon())).into(binding.weatherIconImageView);
        setAdditionalInfo(info);
    }
    private void setAdditionalInfo(FullWeatherInfo info) {
        Current current = info.getCurrent();
        binding.pressureTextView.setText(getString(R.string.pressure, current.getPressure()));
        binding.windChillTextView.setText(getString(R.string.wind_gust, current.getWindGust()));
        binding.cloudCoverTextView.setText(getString(R.string.cloud_cover, current.getClouds()));
        binding.uvIndexTextView.setText(getString(R.string.uv_index, current.getUvi()));
        binding.sunriseTextView.setText(getString(R.string.sunrise, TimeDateService.unixTimeToHh_mm((long) info.getCurrent().getSunrise())));
        binding.sunsetTextView.setText(getString(R.string.sunset, TimeDateService.unixTimeToHh_mm((long) info.getCurrent().getSunset())));
    }

    public void displayFahrenheitTemp(String temp) {
        binding.temperatureTextView.setText(temp);
        binding.temperatureSymbolTextView.setText(TemperatureService.getFahrenheitSym());
    }

    public void displayCelsiusTemp(String temp) {
        binding.temperatureTextView.setText(temp);
        binding.temperatureSymbolTextView.setText(TemperatureService.getCelsiusSym());
    }

    public void showLoader() {
        binding.loadingLayout.loadingLayout.setVisibility(View.VISIBLE);
    }

    public void hideLoader() {
        binding.swipeRefresh.setRefreshing(false);
        binding.loadingLayout.loadingLayout.setVisibility(View.GONE);
    }

    public void showSnackBar() {
        snackbar.show();
    }

    public void hideSnackBar() {
        if (snackbar.isShown()) snackbar.dismiss();
    }

    public void showMissingPermissionsLayout() {
        binding.permissionsLayout.permissionsMissingLayout.setVisibility(View.VISIBLE);
    }
//    public void hideMissingPermissionsLayout() {
//        binding.permissionsLayout.permissionsMissingLayout.setVisibility(View.INVISIBLE);
//    }
}