
package com.example.covid24.model.datamodel.pojo.countrypojo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CountryPOJO {

    private List<Object> errors;
    private String get;
    private Parameters parameters;
    @SerializedName("response")
    private List<Response> statistics;
    private Long results;


    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public List<Response> getStatistics() {
        return statistics;
    }

    public void setResponse(List<Response> response) {
        this.statistics = response;
    }

    public Long getResults() {
        return results;
    }

    public void setResults(Long results) {
        this.results = results;
    }

}
