package com.example.covid24.view.ui.details_screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.covid24.R;
import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.model.datamodel.section.Section;
import com.example.covid24.view.adapter.SectionsRecyclerAdapter;
import com.example.covid24.view.callback.OnCalendarDateChangeListener;
import com.example.covid24.view.callback.OnSaveCountryClickListener;
import com.example.covid24.view.ui.main_screen.countries_screen.CountriesFrag;
import com.example.covid24.viewmodel.MyViewModelFactory;
import com.example.covid24.viewmodel.details_screen.DetailsActivityVM;

import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DetailsActivityVM viewModel;
    private ProgressBar progressBar;
    private static final String INTENT_TAG = CountriesFrag.INTENT_TAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        this.recyclerView = findViewById(R.id.recyclerview_sectionsRecycler);
        this.progressBar = findViewById(R.id.progress_circular);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Country country = intent.getParcelableExtra(INTENT_TAG);

        this.viewModel = new ViewModelProvider(this, new MyViewModelFactory(getApplication(), country)).get(DetailsActivityVM.class);
        this.viewModel.getStatistics();
        this.viewModel.get_sectionList().observe(this, new Observer<List<Section>>() {
            @Override
            public void onChanged(List<Section> sectionList) {
                updateView(sectionList);
            }
        });
    }

    /**
     * Initializes and Passes a Sections list to the sections hybrid recycler adapter.
     * Note: This fragment holds a list of section layouts, instead of a single layout.
     * @param sectionsList
     */
    private void updateView(List<Section> sectionsList) {
        SectionsRecyclerAdapter adapter = new SectionsRecyclerAdapter(DetailsActivity.this, sectionsList, new OnSaveCountryClickListener() {
            @Override
            public void OnClick() {
                DetailsActivity.this.viewModel.updateSavedStatus();
            }
        });
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
        adapter.notifyDataSetChanged();
        this.progressBar.setVisibility(View.GONE);
    }

    /**
     * Shows calendar and listens for user's selected date.
     * On date selected, the activity request the viewModel to update its state according to the user's selected date.
     */
    private void showCalendar() {
        MyCustomCalendar customCalendar = new MyCustomCalendar(DetailsActivity.this, new OnCalendarDateChangeListener() {
            @Override
            public void OnDateChange(String date) {
                viewModel.getStatistics(date);
            }
        });
        customCalendar.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.calendar_menu_item, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        showCalendar();
        return super.onOptionsItemSelected(item);
    }
}
