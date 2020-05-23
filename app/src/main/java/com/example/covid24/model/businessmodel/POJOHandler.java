package com.example.covid24.model.businessmodel;

import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.model.datamodel.country.Statistics;
import com.example.covid24.model.datamodel.pojo.countrypojo.Cases;
import com.example.covid24.model.datamodel.pojo.countrypojo.CountryPOJO;
import com.example.covid24.model.datamodel.pojo.countrypojo.Deaths;
import com.example.covid24.model.datamodel.pojo.countrypojo.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class POJOHandler {

    // Takes a POJO object and returns a Country object
    public Country getWorldInstance(CountryPOJO fetchedCountryPOJO) {
        Country worldInstance;
        if (fetchedCountryPOJO != null) {
            worldInstance = new Country(fetchedCountryPOJO.getParameters().getCountry());
            return worldInstance;
        } else
            return null;
    }


    // Takes a POJO object and returns a Map of statistics
    public Map<String, String> getStatistics(CountryPOJO fetchedCountryPOJO) {
        List<Response> fetchedStatisticsList = fetchedCountryPOJO.getStatistics();
        if (fetchedStatisticsList.isEmpty()) {
            return null;
        }
        Response fetchedStatistics = fetchedCountryPOJO.getStatistics().get(0);
        String dataDate = fetchedCountryPOJO.getStatistics().get(0).getDay();
        Cases fetchedCases = fetchedStatistics.getCases();
        Deaths fetchedDeaths = fetchedStatistics.getDeaths();

        return getStatisticsAsString(dataDate, fetchedCases, fetchedDeaths);
    }


    public Map<String, String> getStatisticsMap(CountryPOJO fetchedCountryPOJO) {
        List<Response> fetchedStatisticsList = fetchedCountryPOJO.getStatistics();
        if (fetchedStatisticsList.isEmpty()) {
            return null;
        }
        Response fetchedStatistics = fetchedCountryPOJO.getStatistics().get(0);
        String dataDate = fetchedCountryPOJO.getStatistics().get(0).getDay();
        Cases fetchedCases = fetchedStatistics.getCases();
        Deaths fetchedDeaths = fetchedStatistics.getDeaths();
        Map<String, String> statisticsMap = getStatisticsAsString(dataDate, fetchedCases, fetchedDeaths);

        return statisticsMap;
    }

    public String getDataData(CountryPOJO fetchedCountryPOJO) {
        if (!fetchedCountryPOJO.getStatistics().isEmpty())
        return fetchedCountryPOJO.getStatistics().get(0).getDay();
        return null;
    }

    private Map<String, String> getStatisticsAsString(String dataDate, Cases fetchedCases, Deaths fetchedDeaths) {
        Map<String, String> statisticsMap = new HashMap<>();

        statisticsMap.put("dataDate", dataDate);
        statisticsMap.put("newCases", fetchedCases.getNew());
        statisticsMap.put("activeCases", fetchedCases.getActive().toString());
        statisticsMap.put("criticalCases", fetchedCases.getCritical().toString());
        statisticsMap.put("recoveredCases", fetchedCases.getRecovered().toString());
        statisticsMap.put("totalCases", fetchedCases.getTotal().toString());
        statisticsMap.put("newDeaths", fetchedDeaths.getNew());
        statisticsMap.put("totalDeaths", fetchedDeaths.getTotal().toString());

        return statisticsMap;
    }
}
