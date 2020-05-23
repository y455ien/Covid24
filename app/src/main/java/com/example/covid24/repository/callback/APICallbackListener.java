package com.example.covid24.repository.callback;

import com.example.covid24.model.datamodel.pojo.countrypojo.CountryPOJO;

import java.util.List;

public interface APICallbackListener {

    // Listens for API Data response as a POJO.
    interface OnPOJOResponseListener {
        void OnPOJODataReceive(CountryPOJO fetchedCountryPOJO);
    }

    // Listens for API Data response of Country names as a List<String>.
    interface OnNamesResponseListener {
        void OnCountriesNamesResponse(List<String> countriesNames);
    }
}
