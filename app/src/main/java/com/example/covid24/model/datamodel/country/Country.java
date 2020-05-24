package com.example.covid24.model.datamodel.country;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "country_data")
public class Country implements Parcelable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "country_name")
    private final String countryName;

    @ColumnInfo(name = "data_date")
    private String dataDate;

    @ColumnInfo(name = "flag_url")
    private String flagURL;

    @ColumnInfo(name = "statistics_data")
    @Nullable
    private Statistics statistics;

    @ColumnInfo(name = "saved_list")
    private boolean isSaved = false;

    public Country(@NonNull String countryName) {
        this.countryName = countryName;
    }

    @NonNull
    public String getCountryName() {
        return countryName;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public String getDataDate() {
        return dataDate;
    }

    public String getFlagURL() {
        return flagURL;
    }

    public void setFlagURL(String flagURL) {
        this.flagURL = flagURL;
    }

    @Nullable
    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(@Nullable Statistics statistics) {
        this.statistics = statistics;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public void updateSavedStatus() {
        if (!isSaved) {
            setSaved(true);
        } else {
            setSaved(false);
        }
    }

    // Parcelable implementation----------------------------------

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.countryName);
        dest.writeString(this.dataDate);
        dest.writeString(this.flagURL);
        dest.writeParcelable(this.statistics, flags);
        dest.writeByte(this.isSaved ? (byte) 1 : (byte) 0);
    }

    protected Country(Parcel in) {
        this.countryName = in.readString();
        this.dataDate = in.readString();
        this.flagURL = in.readString();
        this.statistics = in.readParcelable(Statistics.class.getClassLoader());
        this.isSaved = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Country> CREATOR = new Parcelable.Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel source) {
            return new Country(source);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
}
