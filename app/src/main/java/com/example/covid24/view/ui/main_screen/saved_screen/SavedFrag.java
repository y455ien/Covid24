package com.example.covid24.view.ui.main_screen.saved_screen;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.covid24.R;
import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.view.adapter.SpacesItemDecoration;
import com.example.covid24.view.adapter.CountriesRecyclerAdapter;
import com.example.covid24.view.callback.OnCountryClickListener;
import com.example.covid24.view.ui.details_screen.DetailsActivity;
import com.example.covid24.viewmodel.main_screen.saved_screen.SavedFragVM;

import java.util.List;

public class SavedFrag extends Fragment {

    private SavedFragVM viewModel;
    private RecyclerView countriesRecyclerView;
    private CountriesRecyclerAdapter countriesRecyclerAdapter;
    private ProgressBar progressBar;
    private static final String INTENT_TAG = "CLICKED_COUNTRY";

    public SavedFrag() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = new ViewModelProvider(getActivity()).get(SavedFragVM.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved, container, false);
        this.countriesRecyclerView = view.findViewById(R.id.recyclerview_savedCountriesRecycler);
        this.progressBar = view.findViewById(R.id.progress_circular);
        this.viewModel.get_savedCountryList().observe(getViewLifecycleOwner(), new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                updateView(countries);
            }
        });
        return view;
    }

    /**
     * Request the saved countries list from the viewModel.
     * NOTE: The request's scope must be within the onResume()'s scope, to continuously update data upon user's interactions (Add/Remove from list)
     */
    @Override
    public void onResume() {
        super.onResume();
        this.viewModel.getCountryList();
    }

    /**
     * Initialize and Passes a list of user's Saved countries to the countries recycler adapter.
     * @param countriesList
     */
    private void updateView(List<Country> countriesList) {
        countriesRecyclerAdapter = new CountriesRecyclerAdapter(getActivity(), countriesList, new OnCountryClickListener() {
            @Override
            public void OnClick(Country clickedCountry) {
                Intent i = new Intent(getContext(), DetailsActivity.class);
                i.putExtra(INTENT_TAG, clickedCountry);
                startActivity(i);
            }
        });
        countriesRecyclerView.setAdapter(countriesRecyclerAdapter);
        countriesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        countriesRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
        countriesRecyclerAdapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
    }
}