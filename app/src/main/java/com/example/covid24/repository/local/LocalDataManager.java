package com.example.covid24.repository.local;

import android.content.Context;
import android.util.Log;

import com.amitshekhar.DebugDB;
import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.model.datamodel.country.Statistics;
import com.example.covid24.repository.callback.DatabaseCallbackListener;
import com.example.covid24.repository.callback.InteractorCallbackListener;
import com.example.covid24.repository.local.room.DatabaseHandler;

import java.util.List;

public class LocalDataManager {

    private DatabaseHandler databaseHandler;

    public LocalDataManager(Context context) {
        this.databaseHandler = new DatabaseHandler(context);
        Log.i("REPO", DebugDB.getAddressLog());
    }

    //CACHING Queries
    public void cacheWorld(Country world, DatabaseCallbackListener.OnDataCacheListener repoListener) {
        this.databaseHandler.cacheWorld(world, repoListener);
    }

    public void cacheCountryList(List<Country> countryList, DatabaseCallbackListener.OnDataCacheListener repoListener) {
        this.databaseHandler.cacheCountryList(countryList, repoListener);
    }

    public void cacheStatistics(Country country, Statistics statistics, DatabaseCallbackListener.OnDataCacheListener repoListener) {
        String countryName = country.getCountryName();
        String dataDate = country.getDataDate();
        this.databaseHandler.cacheStatistics(countryName, dataDate, statistics, repoListener);
    }

    public void updateSavedStatus(Country country) {
        String countryName = country.getCountryName();
        boolean isSavedStatus = country.isSaved();
        this.databaseHandler.updateSavedStatus(countryName, isSavedStatus);
    }

    //-----------------------------------------------------------------
    // GET Queries
    public void getCachedCountry(Country country, final InteractorCallbackListener.OnCachedCountryReceiveListener countryStatisticsRepositoryListener) {
        String countryName = country.getCountryName();
        this.databaseHandler.getCachedCountry(countryName, countryStatisticsRepositoryListener);
    }

    public void getCachedCountryList(final InteractorCallbackListener.OnCachedCountryListReceiveListener countryListRepositoryListener) {
        this.databaseHandler.getCachedCountryList(countryListRepositoryListener);
    }

    public void getSavedCountryList(final InteractorCallbackListener.OnCachedCountryListReceiveListener countryListRepositoryListener) {
        this.databaseHandler.getSavedCountryList(countryListRepositoryListener);
    }
}
