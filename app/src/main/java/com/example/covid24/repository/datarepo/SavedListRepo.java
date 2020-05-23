package com.example.covid24.repository.datarepo;

import android.content.Context;

import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.repository.ListRepo;
import com.example.covid24.repository.callback.InteractorCallbackListener.OnCachedCountryListReceiveListener;
import com.example.covid24.repository.callback.ViewModelCallbackListener.OnCountryListReadyListener;

import java.util.List;

public class SavedListRepo extends ListRepo {

    public SavedListRepo(Context context) {
        super(context);
    }

    @Override
    public void getData(final OnCountryListReadyListener viewModelListener) {
        getAndReturnCachedCountryList(viewModelListener);
    }

    // Requests the user's saved List from the localDataManager.
    private void getAndReturnCachedCountryList(final OnCountryListReadyListener viewModelListener) {
        this.localDataManager.getSavedCountryList(new OnCachedCountryListReceiveListener() {
            @Override
            public void OnCachedCountryListReceive(List<Country> countryList) {
                viewModelListener.OnCountryListReady(countryList);
            }
        });
    }
}
