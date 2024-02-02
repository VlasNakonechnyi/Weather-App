package com.fivesysdev.weatherapp;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fivesysdev.weatherapp.adapters.WeatherAppAdapter;
import com.fivesysdev.weatherapp.contract.MainContract;
import com.fivesysdev.weatherapp.databinding.ActivityMainBinding;
import com.fivesysdev.weatherapp.model.FullWeatherInfo;
import com.fivesysdev.weatherapp.model.Hourly;
import com.fivesysdev.weatherapp.model.Weather;
import com.fivesysdev.weatherapp.presenter.WeatherPresenter;
import com.fivesysdev.weatherapp.service.Direction;
import com.fivesysdev.weatherapp.service.IconService;
import com.fivesysdev.weatherapp.service.TemperatureService;
import com.fivesysdev.weatherapp.service.TimeDateService;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements MainContract.View {
    private ActivityMainBinding binding;
    @Inject
    MainContract.Presenter presenter;
    WeatherAppAdapter appAdapter;
    Snackbar snackbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        appAdapter= new WeatherAppAdapter();
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


        presenter.loadFullWeatherInfo();


        binding.swipeRefresh.setOnRefreshListener(() -> {
            presenter.loadFullWeatherInfo();
        });
    }

    @SuppressLint("DefaultLocale")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void displayWeatherInfo(FullWeatherInfo info) {
        List<Hourly> list = info.getHourly();
        System.out.println(list);
        appAdapter.setHourlyList(list);

        binding.hourlyRecyclerView.setAdapter(appAdapter);

        Date date = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFor = new SimpleDateFormat("E, dd MMM, yyyy");
        String stringDate = dateFor.format(date);
        Double temperature = info.getCurrent().getTemp();
        String windInfo = "Wind: " +
                info.getCurrent().getWindSpeed() +
                "m/s, " + Direction.closestToDegrees(info.getCurrent().getWindDeg()).name();

        String rainProb = getString(R.string.Humidity)+ ": " + info.getCurrent().getHumidity() + "%";
        String stringTime = TimeDateService.unixTimeToHh_mm((long)info.getCurrent().getDt());

        String locationString = info.getTimezone();
        Weather current = info.getCurrent().getWeather().get(0);
        String clouds = current.getDescription();


        binding.dateTextView.setText(stringDate);
        binding.temperatureTextView.setText(TemperatureService.fromKelvinToCelsius(temperature));
        binding.temperatureSymbolTextView.setText(TemperatureService.getCelsiusSym());

        binding.timeTextView.setText(stringTime);
        binding.windTextView.setText(windInfo);
        binding.rainProbabilityTextView.setText(rainProb);
        binding.skyClarityText.setText(clouds);
        binding.switch1.setOnCheckedChangeListener((buttonView, isChecked) -> presenter.temperatureSwitch(isChecked, temperature));
        binding.locationTextView.setText(locationString);
        Picasso.get().load(IconService.getIconUrl(current.getIcon())).into(binding.imageView);
        Picasso.get().load(IconService.getIconUrl(current.getIcon())).into(binding.weatherIconImageView);
        binding.pressureTextView.setText(String.format("%s: %d",getString(R.string.Pressure), info.getCurrent().getPressure()));
        binding.windChillTextView.setText(String.format("%s: %f",getString(R.string.Wind_gust), info.getCurrent().getWindGust()));
        binding.cloudCoverTextView.setText(String.format("%s: %d%%",getString(R.string.Cloud_cover), info.getCurrent().getClouds()));
        binding.uVIndexTextView.setText(String.format("%s: %f",getString(R.string.UV_index), info.getCurrent().getUvi()));
        binding.sunriseTextView.setText(String.format("%s: %s",getString(R.string.Sunrise), TimeDateService.unixTimeToHh_mm((long)info.getCurrent().getSunrise())));
        binding.sunsetTextView.setText(String.format("%s: %s",getString(R.string.Sunset), TimeDateService.unixTimeToHh_mm((long)info.getCurrent().getSunset())));
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
        binding.loadingLayout.loadingLayout.setVisibility(View.INVISIBLE);
    }
    public void showSnackBar() {
        snackbar.show();
    }
    public void hideSnackBar() {
        snackbar.dismiss();
    }
}