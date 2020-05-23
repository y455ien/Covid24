package com.example.covid24.repository;

import android.content.Context;

import com.example.covid24.repository.callback.ViewModelCallbackListener.OnCountryListReadyListener;

public abstract class ListRepo extends Repository {

    public ListRepo(Context context) {
        super(context);
    }

    // Requests data from the appropriate data repository.
    public abstract void getData(final OnCountryListReadyListener viewModelListener);

}
