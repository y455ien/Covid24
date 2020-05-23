package com.example.covid24.model.datamodel.section;

import com.example.covid24.model.datamodel.country.Country;

public class DeathsSection extends Section {

    private Country country;
    private String newDeaths, totalDeaths;

    public DeathsSection(Country country) {
        this.country = country;
        setValues();
    }

    private void setValues() {
        this.newDeaths = this.country.getStatistics().getNewDeaths();
        this.totalDeaths = this.country.getStatistics().getTotalDeaths();
    }

    public Country getCountry() {
        return country;
    }

    public String getNewDeaths() {
        return newDeaths;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }
}
