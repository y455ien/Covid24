package com.example.covid24.viewmodel.main_screen.countries_screen;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.repository.ListRepo;
import com.example.covid24.repository.datarepo.CountryListDataRepo;
import com.example.covid24.repository.callback.ViewModelCallbackListener;

import java.util.List;

public class CountriesFragVM extends AndroidViewModel {

    private ListRepo countryListRepository;
    private MutableLiveData<List<Country>> countryList = new MutableLiveData<>();
    private LiveData<List<Country>> _countryList = countryList;

    public CountriesFragVM(@NonNull Application application) {
        super(application);
        this.countryListRepository = new CountryListDataRepo(application);
    }

    // Request a list of all Available countries from the repository
    public void getCountryList() {
        this.countryListRepository.getData(new ViewModelCallbackListener.OnCountryListReadyListener() {
            @Override
            public void OnCountryListReady(List<Country> countryList) {
                notifyView(countryList);
            }
        });
    }

    // Posts a value (countries List) to the observed liveData.
    private void notifyView(List<Country> countryList) {
        this.countryList.postValue(countryList);
    }

    public LiveData<List<Country>> get_countryList() {
        return _countryList;
    }
}
