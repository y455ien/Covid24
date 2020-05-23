package com.example.covid24.repository.local.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.covid24.model.datamodel.country.Country;

@Database(entities = {Country.class}, version = 1, exportSchema = false)
@TypeConverters({StatisticsTypeConverter.class})
public abstract class CountriesDatabase extends RoomDatabase {

    public abstract CountryDao countryDao();

}
