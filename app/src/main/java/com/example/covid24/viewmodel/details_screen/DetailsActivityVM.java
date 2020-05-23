package com.example.covid24.viewmodel.details_screen;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.model.datamodel.section.Section;
import com.example.covid24.repository.CountryRepo;
import com.example.covid24.repository.datarepo.StatisticsDataRepo;
import com.example.covid24.repository.callback.ViewModelCallbackListener.OnSectionListReadyListener;

import java.util.List;

public class DetailsActivityVM extends AndroidViewModel {

    private final Country COUNTRY;
    private CountryRepo countryStatisticsRepository;
    private MutableLiveData<List<Section>> sectionList = new MutableLiveData<>();
    private LiveData<List<Section>> _sectionList = sectionList;


    public DetailsActivityVM(@NonNull Application application, Country country) {
        super(application);
        this.COUNTRY = country;
        this.countryStatisticsRepository = new StatisticsDataRepo(application);
    }

    // Request data from repository.
    public void getStatistics() {
        this.countryStatisticsRepository.getData(this.COUNTRY, null, new OnSectionListReadyListener() {
            @Override
            public void OnSectionListReady(List<Section> sectionList) {
                notifyView(sectionList);
            }
        });
    }

    // Request data from repository on a specific date. (Overloaded Method)
    public void getStatistics(String dateQuery) {
        this.countryStatisticsRepository.getData(this.COUNTRY, dateQuery, new OnSectionListReadyListener() {
            @Override
            public void OnSectionListReady(List<Section> sectionList) {
                notifyView(sectionList);
            }
        });
    }

    // Adds this.Country to the user saved country list.
    public void updateSavedStatus() {
        this.countryStatisticsRepository.updateSavedStatus(COUNTRY);
    }

    // Notifies the view on data change.
    private void notifyView(List<Section> sectionList) {
        DetailsActivityVM.this.sectionList.postValue(sectionList);
    }

    public LiveData<List<Section>> get_sectionList() {
        return _sectionList;
    }

}
