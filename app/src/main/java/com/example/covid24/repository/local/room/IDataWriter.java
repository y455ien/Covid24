package com.example.covid24.repository.local.room;

import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.model.datamodel.country.Statistics;
import com.example.covid24.repository.callback.DatabaseCallbackListener.OnDataCacheListener;

import java.util.List;

public interface IDataWriter {

    public void cacheWorld(final Country world, final OnDataCacheListener localDataManagerListener);

    public void cacheStatistics(final String countryName, final String dataDate, final Statistics statistics, final OnDataCacheListener localDataManagerListener);

    public void cacheCountryList(final List<Country> countriesList, final OnDataCacheListener localDataManagerListener);

    public void updateSavedStatus(final String countryName, final boolean isSavedStatus);

}
