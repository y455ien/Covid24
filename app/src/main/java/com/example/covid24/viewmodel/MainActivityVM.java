package com.example.covid24.viewmodel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.ViewPager;

import com.example.covid24.view.ui.main_screen.countries_screen.CountriesFrag;
import com.example.covid24.view.ui.main_screen.saved_screen.SavedFrag;
import com.example.covid24.view.ui.main_screen.world_screen.WorldFrag;

import java.util.ArrayList;
import java.util.List;

public class MainActivityVM extends ViewModel {
    /**
     * This class holds the state of the MainActivity.
     */


    private MutableLiveData<List<Fragment>> fragmentList = new MutableLiveData<>();
    private LiveData<List<Fragment>> _fragmentList = fragmentList;

    // Initializes activity's viewPager state
    public void initFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new WorldFrag());
        fragmentList.add(new CountriesFrag());
        fragmentList.add(new SavedFrag());
        this.fragmentList.setValue(fragmentList);
    }

    public LiveData<List<Fragment>> get_fragmentList() {
        return _fragmentList;
    }
}
