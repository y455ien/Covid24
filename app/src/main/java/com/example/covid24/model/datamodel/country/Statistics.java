package com.example.covid24.model.datamodel.country;

import android.os.Parcel;
import android.os.Parcelable;

public class Statistics implements Parcelable {

    private String newCases, activeCases, criticalCases, recoveredCases, totalCases;

    private String newDeaths, totalDeaths;

    public String getNewCases() {
        return newCases;
    }

    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }

    public String getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(String activeCases) {
        this.activeCases = activeCases;
    }

    public String getCriticalCases() {
        return criticalCases;
    }

    public void setCriticalCases(String criticalCases) {
        this.criticalCases = criticalCases;
    }

    public String getRecoveredCases() {
        return recoveredCases;
    }

    public void setRecoveredCases(String recoveredCases) {
        this.recoveredCases = recoveredCases;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    public String getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(String newDeaths) {
        this.newDeaths = newDeaths;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    // Parcelable implementation------------------------------------------

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.newCases);
        dest.writeString(this.activeCases);
        dest.writeString(this.criticalCases);
        dest.writeString(this.recoveredCases);
        dest.writeString(this.totalCases);
        dest.writeString(this.newDeaths);
        dest.writeString(this.totalDeaths);
    }

    public Statistics() {
    }

    protected Statistics(Parcel in) {
        this.newCases = in.readString();
        this.activeCases = in.readString();
        this.criticalCases = in.readString();
        this.recoveredCases = in.readString();
        this.totalCases = in.readString();
        this.newDeaths = in.readString();
        this.totalDeaths = in.readString();
    }

    public static final Parcelable.Creator<Statistics> CREATOR = new Parcelable.Creator<Statistics>() {
        @Override
        public Statistics createFromParcel(Parcel source) {
            return new Statistics(source);
        }

        @Override
        public Statistics[] newArray(int size) {
            return new Statistics[size];
        }
    };
}
