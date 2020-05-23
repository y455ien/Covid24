package com.example.covid24.repository;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.repository.callback.ViewModelCallbackListener;

public abstract class WorldRepo extends Repository {

    public WorldRepo(Context context) {
        super(context);
    }

    // Requests data from the appropriate data repository.
    public abstract void getData(final Country country, @Nullable String dateQuery, final ViewModelCallbackListener.OnSectionListReadyListener viewModelListener);

}
