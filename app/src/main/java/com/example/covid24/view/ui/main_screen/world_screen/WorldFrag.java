package com.example.covid24.view.ui.main_screen.world_screen;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.covid24.R;
import com.example.covid24.model.datamodel.section.Section;
import com.example.covid24.view.adapter.SectionsRecyclerAdapter;
import com.example.covid24.view.callback.OnSaveCountryClickListener;
import com.example.covid24.viewmodel.main_screen.world_screen.WorldFragVM;
import java.util.List;


public class WorldFrag extends Fragment {

    private WorldFragVM iViewModel;
    private RecyclerView sectionsRecycler;
    private ProgressBar progressBar;

    public WorldFrag() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.iViewModel = new ViewModelProvider(getActivity()).get(WorldFragVM.class);
        this.iViewModel.getStatistics();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_world, container, false);
        sectionsRecycler = view.findViewById(R.id.recyclerview_sectionsRecycler);
        progressBar = view.findViewById(R.id.progress_circular);
        this.iViewModel.get_sectionList().observe(getViewLifecycleOwner(), new Observer<List<Section>>() {
            @Override
            public void onChanged(List<Section> sectionList) {
                updateView(sectionList);
            }
        });
        return view;
    }

    /**
     * Initialize and Passes a Sections list to the sections hybrid recycler adapter.
     * Note: This fragment holds a list of section layouts, instead of a single layout.
     * @param sectionList
     */
    private void updateView(List<Section> sectionList) {
        SectionsRecyclerAdapter sectionsRecyclerAdapter = new SectionsRecyclerAdapter(getActivity(), sectionList, new OnSaveCountryClickListener() {
            @Override
            public void OnClick() {

            }
        });
        sectionsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        sectionsRecycler.setAdapter(sectionsRecyclerAdapter);
        sectionsRecyclerAdapter.notifyDataSetChanged();
        this.progressBar.setVisibility(View.GONE);
    }
}
