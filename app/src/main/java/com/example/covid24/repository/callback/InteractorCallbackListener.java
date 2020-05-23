package com.example.covid24.repository.callback;

import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.model.datamodel.country.Statistics;

import java.util.List;

public interface InteractorCallbackListener {

    // Listens for Remote updated World Data receive.
    interface OnUpdatedDataReceiveListener {
        void OnReceive(Country world);
    }

    // Listens for Remote updated Countries List receive.
    interface OnCountriesListReceiveListener {
        void OnCountriesListReceive(List<Country> countryList);
    }

    // Listens for Remote updated Country Statistics receive.
    interface OnStatisticsInstanceReceiveListener {
        void OnStatisticsInstanceReceive(Statistics statisticsInstance);
    }

    // Listens for Local cached Countries List receive.
    interface OnCachedCountryListReceiveListener {
        void OnCachedCountryListReceive(List<Country> countryList);
    }

    // Listens for Local cached Country Statistics receive.
    interface OnCachedCountryReceiveListener {
        void OnCachedCountryStatisticsReceive(Country cachedCountry);
    }
}
