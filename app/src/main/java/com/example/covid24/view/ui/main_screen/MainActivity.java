package com.example.covid24.view.ui.main_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.covid24.R;
import com.example.covid24.view.adapter.ViewPagerAdapter;
import com.example.covid24.view.listener.MyTabLayoutListener;
import com.example.covid24.viewmodel.MainActivityVM;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    MyTabLayoutListener tabsListener;
    MainActivityVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        setSupportActionBar(toolbar);
        viewModel = new ViewModelProvider(this).get(MainActivityVM.class);
        viewModel.initFragments();
        viewModel.get_fragmentList().observe(this, new Observer<List<Fragment>>() {
            @Override
            public void onChanged(List<Fragment> fragments) {
                updateView(fragments);
            }
        });
    }

    // Initialize and Passes a fragment list to the viewPager
    private void updateView(List<Fragment> fragmentList) {
        tabsListener = new MyTabLayoutListener(this.viewPager);
        tabLayout.addOnTabSelectedListener(tabsListener);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), fragmentList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}
