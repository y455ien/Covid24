package com.example.covid24.view.ui.main_screen.countries_screen;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.covid24.R;
import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.view.adapter.SpacesItemDecoration;
import com.example.covid24.view.adapter.CountriesRecyclerAdapter;
import com.example.covid24.view.callback.OnCountryClickListener;
import com.example.covid24.view.ui.details_screen.DetailsActivity;
import com.example.covid24.viewmodel.main_screen.countries_screen.CountriesFragVM;
import java.util.List;


public class CountriesFrag extends Fragment {

    private CountriesFragVM viewModel;
    private RecyclerView countriesRecyclerView;
    private CountriesRecyclerAdapter countriesRecyclerAdapter;
    private ProgressBar progressBar;
    public static final String INTENT_TAG = "CLICKED_COUNTRY";

    public CountriesFrag() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.viewModel = new ViewModelProvider(getActivity()).get(CountriesFragVM.class);
        this.viewModel.getCountryList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countries, container, false);
        this.countriesRecyclerView = view.findViewById(R.id.recyclerview_countriesRecycler);
        this.progressBar = view.findViewById(R.id.progress_circular);
        this.viewModel.get_countryList().observe(getViewLifecycleOwner(), new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                updateView(countries);
            }
        });
        return view;
    }

    /**
     * Initialize and Passes a list of all Available counties to the countries recycler adapter.
     * On country clicked, it sends the clicked country to the Detail Activity as a parcelable object.
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
        this.progressBar.setVisibility(View.GONE);
    }

    // Inflates and initializes the filterable searchView
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.my_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Enter country name");
        SearchView.SearchAutoComplete theTextArea = searchView.findViewById(R.id.search_src_text);
        theTextArea.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        searchView.setBackground(getResources().getDrawable(R.drawable.search_bar_background));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                countriesRecyclerAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}
