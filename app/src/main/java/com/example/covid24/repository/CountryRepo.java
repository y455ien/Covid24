package com.example.covid24.repository;

import android.content.Context;

import com.example.covid24.model.datamodel.country.Country;

public abstract class CountryRepo extends WorldRepo {

    public CountryRepo(Context context) {
        super(context);
    }

    // Adds a country to the user Saved List.
    public abstract void updateSavedStatus(Country country);

}
