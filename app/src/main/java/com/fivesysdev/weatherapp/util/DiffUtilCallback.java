package com.fivesysdev.weatherapp.util;

import androidx.recyclerview.widget.DiffUtil;

import com.fivesysdev.weatherapp.model.local.Hourly;

import java.util.List;

public class DiffUtilCallback extends DiffUtil.Callback {
    private List<Hourly> oldList;
    private List<Hourly> newList;

    public DiffUtilCallback(List<Hourly> oldList, List<Hourly> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition) == newList.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
