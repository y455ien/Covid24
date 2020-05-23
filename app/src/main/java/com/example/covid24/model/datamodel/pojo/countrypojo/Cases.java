
package com.example.covid24.model.datamodel.pojo.countrypojo;

import com.google.gson.annotations.SerializedName;

public class Cases {

    private Long active;
    private Long critical;
    @SerializedName("new")
    private String newCase;
    private Long recovered;
    private Long total;

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }

    public Long getCritical() {
        return critical;
    }

    public void setCritical(Long critical) {
        this.critical = critical;
    }

    public String getNew() {
        return newCase;
    }

    public void setNew(String newCase) {
        this.newCase = newCase;
    }

    public Long getRecovered() {
        return recovered;
    }

    public void setRecovered(Long recovered) {
        this.recovered = recovered;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}
