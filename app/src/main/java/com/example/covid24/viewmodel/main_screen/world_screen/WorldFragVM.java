package com.example.covid24.viewmodel.main_screen.world_screen;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.model.datamodel.section.Section;
import com.example.covid24.repository.WorldRepo;
import com.example.covid24.repository.datarepo.WorldDataRepo;
import com.example.covid24.repository.callback.ViewModelCallbackListener.OnSectionListReadyListener;

import java.util.List;

public class WorldFragVM extends AndroidViewModel {
    /**
     * This class holds the state of the world fragment
     */

    private final Country COUNTRY = new Country("All");
    private MutableLiveData<List<Section>> sectionList = new MutableLiveData<>();
    private LiveData<List<Section>> _sectionList = sectionList;
    private WorldRepo worldRepository;


    public WorldFragVM(@NonNull Application application) {
        super(application);
        this.worldRepository = new WorldDataRepo(application);
    }

    // Request a list of sections from the repository
    public void getStatistics() {
        this.worldRepository.getData(this.COUNTRY, null, new OnSectionListReadyListener() {
            @Override
            public void OnSectionListReady(List<Section> sectionList) {
                notifyView(sectionList);
            }
        });
    }

    // Posts a value (Sections List) to the observed liveData.
    private void notifyView(List<Section> statisticsSections) {
        this.sectionList.postValue(statisticsSections);
    }

    public LiveData<List<Section>> get_sectionList() {
        return _sectionList;
    }

}
