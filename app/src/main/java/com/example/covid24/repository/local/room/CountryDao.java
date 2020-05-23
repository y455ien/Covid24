package com.example.covid24.repository.local.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.model.datamodel.country.Statistics;

import java.util.List;

@Dao
public interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long cacheWorld(Country world);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long cacheCountry(Country country);

    @Query("UPDATE country_data SET statistics_data =:statistics, data_date =:dataDate WHERE country_name =:countryName")
    void cacheCountryStatistics(String countryName, String dataDate, Statistics statistics);

    @Query("SELECT * FROM country_data WHERE country_name =:countryName")
    Country getCountryStatistics(String countryName);

    @Query("SELECT * FROM country_data WHERE country_name != :exceptionQuery ")
    List<Country> getAllCountries(String exceptionQuery);

    @Query("UPDATE country_data SET saved_list =:isSaved where country_name =:countryName ")
    void updateSavedStatus(String countryName, boolean isSaved);

    @Query("SELECT * FROM country_data WHERE saved_list = 1")
    List<Country> getSavedCountryList();


}
