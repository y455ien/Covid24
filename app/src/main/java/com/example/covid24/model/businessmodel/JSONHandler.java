package com.example.covid24.model.businessmodel;

import android.content.Context;

import com.example.covid24.model.datamodel.pojo.flaglistpojo.CountryFlag;
import com.example.covid24.model.datamodel.pojo.flaglistpojo.CountryFlagList;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONHandler {

    public Map<String, String> getCountryFlagMap(Context context) {
        return loadJSONFromAsset(context);
    }

    // Loads JSON file from assets folder, parse it into a Map<countryName, countryFlagID>
    private Map<String, String> loadJSONFromAsset(Context context) {
        String json;
        try {
            InputStream inputStream = context.getAssets().open("flags_code_json.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return mapJSONToMap(json);
    }

    private Map<String, String> mapJSONToMap(String json) {
        Gson gson = new Gson();
        CountryFlagList countryFlagList = gson.fromJson(json, CountryFlagList.class);
        List<CountryFlag> flagIDList = countryFlagList.getCountries();
        Map<String, String> flagIDs = new HashMap<>();
        for (CountryFlag currentCountryFlag : flagIDList) {
            flagIDs.put(currentCountryFlag.getName(), currentCountryFlag.getCode());
        }
        return flagIDs;
    }

}
