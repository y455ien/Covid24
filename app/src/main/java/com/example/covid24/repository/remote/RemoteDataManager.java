package com.example.covid24.repository.remote;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.covid24.repository.callback.APICallbackListener.OnPOJOResponseListener;
import com.example.covid24.repository.callback.APICallbackListener.OnNamesResponseListener;
import com.example.covid24.repository.remote.api.CountryNameAPIEndpoint;
import com.example.covid24.repository.remote.api.StatisticsHistoryAPIEndpoint;

public class RemoteDataManager {

    private StatisticsHistoryAPIEndpoint statisticsHistoryApiEndpoint;
    private CountryNameAPIEndpoint countryNameAPIEndpoint;


    public RemoteDataManager() {
        this.statisticsHistoryApiEndpoint = new StatisticsHistoryAPIEndpoint();
        this.countryNameAPIEndpoint = new CountryNameAPIEndpoint();
    }


    public void getCountryPOJO(String countryQuery, @Nullable String dateQuery, final OnPOJOResponseListener repoListener) {
        this.statisticsHistoryApiEndpoint.fetchStatistics(countryQuery, dateQuery, repoListener);
        Log.i("REPO", "//----> FETCHING WORLD UPDATED INSTANCE");
    }

    public void getCountryListNames(OnNamesResponseListener repoListener) {
        this.countryNameAPIEndpoint.getCountriesNames(repoListener);
        Log.i("REPO", "//----> FETCHING UPDATED COUNTRIES LIST");
    }

}
