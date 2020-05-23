package com.example.covid24.repository.local.room;

import com.example.covid24.repository.callback.InteractorCallbackListener.OnCachedCountryListReceiveListener;
import com.example.covid24.repository.callback.InteractorCallbackListener.OnCachedCountryReceiveListener;

public interface IDataReader {

    public void getCachedCountry(final String countryName, final OnCachedCountryReceiveListener localDataManagerListener);

    public void getCachedCountryList(final OnCachedCountryListReceiveListener localDataManagerListener);

    public void getSavedCountryList(final OnCachedCountryListReceiveListener localDataManagerListener);

}
