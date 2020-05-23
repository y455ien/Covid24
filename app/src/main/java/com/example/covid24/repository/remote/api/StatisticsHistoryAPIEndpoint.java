package com.example.covid24.repository.remote.api;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.covid24.model.datamodel.pojo.countrypojo.CountryPOJO;
import com.example.covid24.repository.callback.APICallbackListener.OnPOJOResponseListener;


public class StatisticsHistoryAPIEndpoint {

    public void fetchStatistics(String countryQuery, @Nullable String dateQuery, final OnPOJOResponseListener remoteDataManagerListener) {
        if (dateQuery == null) {
            AndroidNetworking.get("https://covid-193.p.rapidapi.com/history")
                    .addHeaders("x-rapidapi-key", "643c672725msh22814daa38e64c4p18bd09jsn98947b0744a6")
                    .addQueryParameter("country", countryQuery)
                    .build()
                    .getAsObject(CountryPOJO.class, new ParsedRequestListener<CountryPOJO>() {
                        @Override
                        public void onResponse(CountryPOJO fetchedCountryPOJO) {
                            remoteDataManagerListener.OnPOJODataReceive(fetchedCountryPOJO);
                        }

                        @Override
                        public void onError(ANError anError) {
                            anError.printStackTrace();
                        }
                    });
        } else {
            AndroidNetworking.get("https://covid-193.p.rapidapi.com/history")
                    .addHeaders("x-rapidapi-key", "643c672725msh22814daa38e64c4p18bd09jsn98947b0744a6")
                    .addQueryParameter("country", countryQuery)
                    .addQueryParameter("day", dateQuery)
                    .build()
                    .getAsObject(CountryPOJO.class, new ParsedRequestListener<CountryPOJO>() {
                        @Override
                        public void onResponse(CountryPOJO fetchedCountryPOJO) {
                            remoteDataManagerListener.OnPOJODataReceive(fetchedCountryPOJO);
                        }

                        @Override
                        public void onError(ANError anError) {
                            anError.printStackTrace();
                        }
                    });
        }
    }
}
