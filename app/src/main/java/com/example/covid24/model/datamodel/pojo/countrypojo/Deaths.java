
package com.example.covid24.model.datamodel.pojo.countrypojo;

import com.google.gson.annotations.SerializedName;

public class Deaths {

    @SerializedName("new")
    private String newDeaths;
    private Long total;

    public String getNew() {
        return newDeaths;
    }

    public void setNew(String newDeaths) {
        this.newDeaths = newDeaths;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}
