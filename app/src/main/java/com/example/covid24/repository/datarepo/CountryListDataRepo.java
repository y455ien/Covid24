package com.example.covid24.repository.datarepo;

import android.content.Context;

import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.repository.ListRepo;
import com.example.covid24.repository.callback.APICallbackListener.OnNamesResponseListener;
import com.example.covid24.repository.callback.DatabaseCallbackListener.OnDataCacheListener;
import com.example.covid24.repository.callback.InteractorCallbackListener.OnCachedCountryListReceiveListener;
import com.example.covid24.repository.callback.InteractorCallbackListener.OnCountriesListReceiveListener;
import com.example.covid24.repository.callback.ViewModelCallbackListener.OnCountryListReadyListener;

import java.util.List;

public class CountryListDataRepo extends ListRepo {
    /**
     * This class extends the Country Repo class
     * This repository class follows the Offline first architecture (Only as good as i could =])
     * The Local database is considered the only source of data.
     * @param context
     */

    public CountryListDataRepo(Context context) {
        super(context);
    }

    /**
     * If there is an active internet connection, requests data from the remoteDataManager,
     * Caches the new data updates, and then requests the cached data again
     *
     * if there is no active internet connection, requests cached data directly from the local database
     * @param viewModelListener
     */
    @Override
    public void getData(final OnCountryListReadyListener viewModelListener) {
        if (isConnectedToInternet()) {
            getCountryListUpdates(new OnCountriesListReceiveListener() {
                @Override
                public void OnCountriesListReceive(List<Country> countryList) {
                    cacheCountryList(countryList, new OnDataCacheListener() {
                        @Override
                        public void OnCache() {
                            getCachedCountryList(viewModelListener);
                        }
                    });
                }
            });
        } else {
            getCachedCountryList(viewModelListener);
        }
    }

    private void getCountryListUpdates(final OnCountriesListReceiveListener listener) {
        this.remoteDataManager.getCountryListNames(new OnNamesResponseListener() {
            @Override
            public void OnCountriesNamesResponse(List<String> countriesNames) {
                List<Country> countryInstancesList = CountryListDataRepo.this.interactor.getCountryList(countriesNames);
                listener.OnCountriesListReceive(countryInstancesList);
            }
        });
    }


    private void cacheCountryList(List<Country> countryList, OnDataCacheListener listener) {
        this.localDataManager.cacheCountryList(countryList, listener);
    }

    private void getCachedCountryList(final OnCountryListReadyListener viewModelListener) {
        this.localDataManager.getCachedCountryList(new OnCachedCountryListReceiveListener() {
            @Override
            public void OnCachedCountryListReceive(List<Country> countryList) {
                viewModelListener.OnCountryListReady(countryList);
            }
        });

    }
}
