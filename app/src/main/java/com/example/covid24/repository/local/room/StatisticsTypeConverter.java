package com.example.covid24.repository.local.room;

import androidx.room.TypeConverter;

import com.example.covid24.model.datamodel.country.Statistics;
import com.google.gson.Gson;

class StatisticsTypeConverter {

    @TypeConverter
    public static Statistics stringToSomeObjectList(String data) {
        Gson gson = new Gson();
        Statistics statistics = gson.fromJson(data, Statistics.class);
        return statistics;
    }


    @TypeConverter
    public static String someObjectListToString(Statistics statistics) {
        Gson gson = new Gson();
        String json = gson.toJson(statistics);
        return json;
    }
}
