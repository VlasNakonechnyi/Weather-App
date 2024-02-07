package com.fivesysdev.weatherapp.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.fivesysdev.weatherapp.R;

import com.fivesysdev.weatherapp.databinding.ItemHourlyInfoBinding;
import com.fivesysdev.weatherapp.model.Hourly;
import com.fivesysdev.weatherapp.model.WeatherInfo;
import com.fivesysdev.weatherapp.service.IconService;
import com.fivesysdev.weatherapp.service.TemperatureService;
import com.fivesysdev.weatherapp.service.TimeDateService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WeatherAppAdapter extends RecyclerView.Adapter<WeatherAppAdapter.ViewHolder> {
    List<Hourly> hourlyList = new ArrayList<>();

    public List<Hourly> getHourlyList() {
        return hourlyList;
    }

    public void setHourlyList(List<Hourly> hourlyList) {
        this.hourlyList = hourlyList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hourly_info, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hourly hourly = hourlyList.get(position);
        WeatherInfo hourlyWeather = hourly.getWeather().get(0);
        holder.time.setText(TimeDateService.unixTimeToHh_mm((long)hourly.getDt()));
        Picasso.get().load(IconService.getIconUrl(hourlyWeather.getIcon())).into(holder.image);
        holder.humidity.setText(String.format("%d%%", hourly.getHumidity()));
        holder.temperature.setText(TemperatureService.fromKelvinToCelsius(hourly.getTemp()));
        holder.description.setText(hourlyWeather.getDescription());
        holder.symbol.setText(TemperatureService.getCelsiusSym());
    }

    @Override
    public int getItemCount() {
        return hourlyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemHourlyInfoBinding binding;
        TextView time;
        ImageView image;
        TextView temperature;
        TextView description;
        TextView humidity;
        TextView symbol;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemHourlyInfoBinding.bind(itemView);
            time = binding.timeHourlyTextVIew;
            image = binding.hourlyImageView;
            temperature = binding.hourlyTemperatureTextView;
            description = binding.hourlyDescriptionTextView;
            humidity = binding.humidityTextView;
            symbol = binding.symbol;
        }
    }

}
