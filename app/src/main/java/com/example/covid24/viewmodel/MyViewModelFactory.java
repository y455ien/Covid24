package com.example.covid24.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.viewmodel.details_screen.DetailsActivityVM;

public class MyViewModelFactory implements ViewModelProvider.Factory {
    /**
     * This class acts as a viewModel factory, to create a viewModel that holds the states of a certain country
     */

    private Country country;
    private Application application;

    public MyViewModelFactory(Application application, Country country) {
        this.country = country;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DetailsActivityVM(this.application, country);
    }
}
