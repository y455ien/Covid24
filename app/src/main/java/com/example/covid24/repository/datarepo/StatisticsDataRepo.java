package com.example.covid24.repository.datarepo;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.model.datamodel.country.Statistics;
import com.example.covid24.model.datamodel.pojo.countrypojo.CountryPOJO;
import com.example.covid24.model.datamodel.section.Section;
import com.example.covid24.repository.CountryRepo;
import com.example.covid24.repository.callback.APICallbackListener.OnPOJOResponseListener;
import com.example.covid24.repository.callback.DatabaseCallbackListener.OnDataCacheListener;
import com.example.covid24.repository.callback.InteractorCallbackListener.OnCachedCountryReceiveListener;
import com.example.covid24.repository.callback.InteractorCallbackListener.OnStatisticsInstanceReceiveListener;
import com.example.covid24.repository.callback.ViewModelCallbackListener.OnSectionListReadyListener;

import java.util.List;

public class StatisticsDataRepo extends CountryRepo {
    /**
     * This class extends the Country Repo class
     * This repository class follows the Offline first architecture (Only as good as i could =])
     * The Local database is considered the only source of data.
     * @param context
     */

    public StatisticsDataRepo(Context context) {
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
            getStatisticsUpdates(country, dateQuery, new OnStatisticsInstanceReceiveListener() {
                @Override
                public void OnStatisticsInstanceReceive(Statistics statisticsInstance) {
                    cacheCountryStatistics(country, statisticsInstance, new OnDataCacheListener() {
                        @Override
                        public void OnCache() {
                            getCachedCountryStatistics(country, viewModelListener);
                        }
                    });
                }
            });
        } else {
            getCachedCountryStatistics(country, viewModelListener);
        }
    }

    @Override
    public void updateSavedStatus(Country country) {
        this.localDataManager.updateSavedStatus(country);
    }

    private void getStatisticsUpdates(final Country country, @Nullable String dateQuery, final OnStatisticsInstanceReceiveListener listener) {
        this.remoteDataManager.getCountryPOJO(country.getCountryName(), dateQuery, new OnPOJOResponseListener() {
            @Override
            public void OnPOJODataReceive(CountryPOJO fetchedCountryPOJO) {
                Statistics statisticsInstance = StatisticsDataRepo.this.interactor.getStatisticsInstance(fetchedCountryPOJO);
                String dataDate = StatisticsDataRepo.this.interactor.getDataDate(fetchedCountryPOJO);
                country.setDataDate(dataDate);
                listener.OnStatisticsInstanceReceive(statisticsInstance);
            }
        });
    }

    private void cacheCountryStatistics(Country country, Statistics statistics, OnDataCacheListener listener) {
        this.localDataManager.cacheStatistics(country, statistics, listener);
    }

    private void getCachedCountryStatistics(final Country country, final OnSectionListReadyListener viewModelListener) {
        this.localDataManager.getCachedCountry(country, new OnCachedCountryReceiveListener() {
            @Override
            public void OnCachedCountryStatisticsReceive(Country cachedCountry) {
                country.setStatistics(cachedCountry.getStatistics());
                country.setSaved(cachedCountry.isSaved());
                List<Section> sectionList = StatisticsDataRepo.this.interactor.getSectionsList(country);
                viewModelListener.OnSectionListReady(sectionList);
            }
        });
    }
}
