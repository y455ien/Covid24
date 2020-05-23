package com.example.covid24.repository.remote.api;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.covid24.repository.callback.APICallbackListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CountryNameAPIEndpoint {

    public void getCountriesNames(final APICallbackListener.OnNamesResponseListener remoteDataManagerListener) {
        AndroidNetworking.get("https://covid-193.p.rapidapi.com/countries")
                .addHeaders("x-rapidapi-key", "643c672725msh22814daa38e64c4p18bd09jsn98947b0744a6")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray responseArray = response.optJSONArray("response");
                        List<String> countriesNamesList = new ArrayList<>();
                        for (int i = 0; i < responseArray.length(); i++) {
                            countriesNamesList.add(responseArray.optString(i));
                        }
                        remoteDataManagerListener.OnCountriesNamesResponse(countriesNamesList);
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                    }
                });
    }

}
