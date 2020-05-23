package com.example.covid24.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.covid24.view.ui.main_screen.countries_screen.CountriesFrag;
import com.example.covid24.view.ui.main_screen.saved_screen.SavedFrag;
import com.example.covid24.view.ui.main_screen.world_screen.WorldFrag;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private List<Fragment> fragmentList;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int numOfTabs, List<Fragment> fragmentList) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return fragmentList.get(0);
            case 1:
                return fragmentList.get(1);
            case 2:
                return fragmentList.get(2);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.numOfTabs;
    }
}
