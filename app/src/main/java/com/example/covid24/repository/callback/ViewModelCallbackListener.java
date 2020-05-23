package com.example.covid24.repository.callback;

import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.model.datamodel.section.Section;

import java.util.List;

public interface ViewModelCallbackListener {

    // Listens for Countries List Data ready to update the View.
    interface OnCountryListReadyListener extends ViewModelCallbackListener {
        void OnCountryListReady(List<Country> countryList);
    }

    // Listens for Sections List Data ready to update the View.
    interface OnSectionListReadyListener extends ViewModelCallbackListener {
            void OnSectionListReady(List<Section> sectionList);
    }
}
