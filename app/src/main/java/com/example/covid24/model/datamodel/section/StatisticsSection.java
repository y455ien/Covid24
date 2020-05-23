package com.example.covid24.model.datamodel.section;

import android.widget.ImageView;

import com.example.covid24.model.datamodel.country.Country;

public class StatisticsSection extends Section {

    private Country country;
    private String countryName;
    private String flagURL;
    private String dataDate;
    private String newCases, activeCases, criticalCases, recoveredCases, totalCases;


    public StatisticsSection(Country country) {
        this.country = country;
        setValues();
    }

    private void setValues() {
        this.countryName = this.country.getCountryName();
        this.flagURL = this.country.getFlagURL();
        this.dataDate = this.country.getDataDate();
        this.newCases = this.country.getStatistics().getNewCases();
        this.activeCases = this.country.getStatistics().getActiveCases();
        this.criticalCases = this.country.getStatistics().getCriticalCases();
        this.recoveredCases = this.country.getStatistics().getRecoveredCases();
        this.totalCases = this.country.getStatistics().getTotalCases();
    }

    public String getCountryName() {
        return countryName;
    }

    public String getFlagURL() {
        return flagURL;
    }

    public Country getCountry() {
        return country;
    }

    public String getDataDate() {
        return dataDate;
    }

    public String getNewCases() {
        return newCases;
    }

    public String getActiveCases() {
        return activeCases;
    }

    public String getCriticalCases() {
        return criticalCases;
    }

    public String getRecoveredCases() {
        return recoveredCases;
    }

    public String getTotalCases() {
        return totalCases;
    }
}
