package com.example.covid24.view.listener;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MyTabLayoutListener implements TabLayout.OnTabSelectedListener {

    private ViewPager activityViewPager;

    public MyTabLayoutListener(ViewPager activityViewPager) {
     this.activityViewPager = activityViewPager;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        activityViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
