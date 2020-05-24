package com.example.covid24.model.businessmodel;

import android.content.Context;

import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.model.datamodel.country.Statistics;
import com.example.covid24.model.datamodel.pojo.countrypojo.CountryPOJO;
import com.example.covid24.model.datamodel.section.DeathsSection;
import com.example.covid24.model.datamodel.section.NoDataSection;
import com.example.covid24.model.datamodel.section.Section;
import com.example.covid24.model.datamodel.section.StatisticsSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataInteractor {
    /**
     * This class acts as a middleware between a viewModel and its repository
     * It manipulates data received from the repository to the appropriate form ready to be return to the viewModel.
     */

    private Context context;
    private POJOHandler pojoHandler;
    private JSONHandler JSONHandler;

    public DataInteractor(Context context) {
        this.context = context;
        this.pojoHandler = new POJOHandler();
        this.JSONHandler = new JSONHandler();
    }

    /**
     * Takes a POJO response and returns a Country object.
     * @param fetchedCountryPOJO
     * @return
     */
    public Country getWorldInstance(CountryPOJO fetchedCountryPOJO) {
        if (fetchedCountryPOJO != null) {
            Country worldInstance = this.pojoHandler.getWorldInstance(fetchedCountryPOJO);
            Map<String, String> statisticsMap = this.pojoHandler.getStatisticsMap(fetchedCountryPOJO);
            Statistics statistics = getStatisticsInstance(statisticsMap);
            worldInstance.setStatistics(statistics);
            return worldInstance;
        } else {
            return null;
        }
    }

    /**
     * Takes a POJO response and returns a Country Statistics object.
     * @param fetchedCountryPOJO
     * @return
     */
    public Statistics getStatisticsInstance(CountryPOJO fetchedCountryPOJO) {
        Map<String, String> statisticsMap = this.pojoHandler.getStatistics(fetchedCountryPOJO);
        if (statisticsMap != null)
            return getStatisticsInstance(statisticsMap);
        return null;
    }


    /**
     * Takes a list of countries names response and returns a list of Countries instances.
     * @param fetchedCountriesNames
     * @return
     */
    public List<Country> getCountryList(List<String> fetchedCountriesNames) {
        Map<String, String> flagsMap = this.JSONHandler.getCountryFlagMap(this.context);
        List<Country> countriesList = new ArrayList<>();
        for (String currentCountryName : fetchedCountriesNames) {
            String flagID = flagsMap.get(currentCountryName);
            String flagURL = "https://www.countryflags.io/" + flagID + "/flat/64.png";
            Country currentCountry = new Country(currentCountryName);
            currentCountry.setFlagURL(flagURL);
            countriesList.add(currentCountry);
        }
        return countriesList;
    }

    /**
     * Takes a Country object and returns a list of Statistics sections list.
     * @param country
     * @return
     */
    public List<Section> getSectionsList(Country country) {
        List<Section> sections = new ArrayList<>();
        if (country.getStatistics() == null) {
            NoDataSection noDataSection = new NoDataSection();
            sections.add(noDataSection);
        } else {
            StatisticsSection statisticsSection = new StatisticsSection(country);
            sections.add(statisticsSection);
            DeathsSection deathsSection = new DeathsSection(country);
            sections.add(deathsSection);
        }
        return sections;
    }

    public String getDataDate(CountryPOJO fetchedCountryPojo) {
        return this.pojoHandler.getDataData(fetchedCountryPojo);
    }


    private Statistics getStatisticsInstance(Map<String, String> statisticsMap) {
        Statistics statisticsInstance = new Statistics();

        statisticsInstance.setNewCases(statisticsMap.get("newCases"));
        statisticsInstance.setActiveCases(statisticsMap.get("activeCases"));
        statisticsInstance.setCriticalCases(statisticsMap.get("criticalCases"));
        statisticsInstance.setRecoveredCases(statisticsMap.get("recoveredCases"));
        statisticsInstance.setTotalCases(statisticsMap.get("totalCases"));
        statisticsInstance.setNewDeaths(statisticsMap.get("newDeaths"));
        statisticsInstance.setTotalDeaths(statisticsMap.get("totalDeaths"));

        return statisticsInstance;
    }
}
