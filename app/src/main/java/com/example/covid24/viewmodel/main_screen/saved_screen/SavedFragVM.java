package com.example.covid24.viewmodel.main_screen.saved_screen;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.repository.ListRepo;
import com.example.covid24.repository.datarepo.SavedListRepo;
import com.example.covid24.repository.callback.ViewModelCallbackListener;

import java.util.List;

public class SavedFragVM extends AndroidViewModel {
    /**
     * This class holds the state of the saved countries fragment
     */

    private ListRepo savedListRepository;
    private MutableLiveData<List<Country>> savedCountryList = new MutableLiveData<>();
    private LiveData<List<Country>> _savedCountryList = savedCountryList;

    public SavedFragVM(@NonNull Application application) {
        super(application);
        this.savedListRepository = new SavedListRepo(application);
    }

    // Request a list of user's Saved countries from the repository
    public void getCountryList() {
        this.savedListRepository.getData(new ViewModelCallbackListener.OnCountryListReadyListener() {
            @Override
            public void OnCountryListReady(List<Country> countryList) {
                notifyView(countryList);
            }
        });
    }

    // Posts a value (countries List) to the observed liveData.
    private void notifyView(List<Country> countryList) {
        this.savedCountryList.postValue(countryList);
    }

    public LiveData<List<Country>> get_savedCountryList() {
        return _savedCountryList;
    }
}
