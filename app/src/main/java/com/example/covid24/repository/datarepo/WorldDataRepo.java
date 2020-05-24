package com.example.covid24.repository.datarepo;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.model.datamodel.pojo.countrypojo.CountryPOJO;
import com.example.covid24.model.datamodel.section.Section;
import com.example.covid24.repository.WorldRepo;
import com.example.covid24.repository.callback.APICallbackListener.OnPOJOResponseListener;
import com.example.covid24.repository.callback.DatabaseCallbackListener.OnDataCacheListener;
import com.example.covid24.repository.callback.InteractorCallbackListener.OnCachedCountryReceiveListener;
import com.example.covid24.repository.callback.InteractorCallbackListener.OnUpdatedDataReceiveListener;
import com.example.covid24.repository.callback.ViewModelCallbackListener.OnSectionListReadyListener;

import java.util.List;

public class WorldDataRepo extends WorldRepo {
    /**
     * This class extends the World Repo class
     * This repository class follows the Offline first architecture (Only as good as i could =])
     * The Local database is considered the only source of data.
     * @param context
     */

    public WorldDataRepo(Context context) {
        super(context);
    }


    /**
     * If there is an active internet connection, requests data from the remoteDataManager,
     * Caches the new data updates, and then requests the cached data again
     *
     * if there is no active internet connection, requests cached data directly from the local database
     * @param country
     * @param dateQuery
     * @param viewModelListener
     */
    @Override
    public void getData(final Country country, @Nullable String dateQuery, final OnSectionListReadyListener viewModelListener) {
        if (isConnectedToInternet()) {
            getWorldDataUpdates(country, dateQuery, new OnUpdatedDataReceiveListener() {
                @Override
                public void OnReceive(Country world) {
                    cacheWorldData(world, new OnDataCacheListener() {
                        @Override
                        public void OnCache() {
                            getCachedWorldData(country, viewModelListener);
                        }
                    });
                }
            });
        } else {
            getCachedWorldData(country, viewModelListener);
        }
    }

    private void getWorldDataUpdates(final Country country, @Nullable String dateQuery, final OnUpdatedDataReceiveListener listener) {
        this.remoteDataManager.getCountryPOJO(country.getCountryName(), dateQuery, new OnPOJOResponseListener() {
            @Override
            public void OnPOJODataReceive(CountryPOJO fetchedCountryPOJO) {
                Country worldInstance = WorldDataRepo.this.interactor.getWorldInstance(fetchedCountryPOJO);
                String dataDate = WorldDataRepo.this.interactor.getDataDate(fetchedCountryPOJO);
                country.setDataDate(dataDate);
                listener.OnReceive(worldInstance);
            }
        });
    }

    private void cacheWorldData(Country country, OnDataCacheListener listener) {
        this.localDataManager.cacheWorld(country, listener);
    }

    private void getCachedWorldData(final Country country, final OnSectionListReadyListener viewModelListener) {
        this.localDataManager.getCachedCountry(country, new OnCachedCountryReceiveListener() {
            @Override
            public void OnCachedCountryStatisticsReceive(Country cachedCountry) {
                country.setStatistics(cachedCountry.getStatistics());
                List<Section> sectionList = WorldDataRepo.this.interactor.getSectionsList(country);
                viewModelListener.OnSectionListReady(sectionList);
            }
        });
    }
}
