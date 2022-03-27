package com.kunal456k.moviedatabase.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Country {
    @SerializedName("iso_3166_1")
    private String isoName;

    @PrimaryKey
    @SerializedName("name")
    @NonNull
    private String countryName;

    public Country(@NonNull String countryName) {
        this.countryName = countryName;
    }

    public String getIsoName() {
        return isoName;
    }

    @NonNull
    public String getCountryName() {
        return countryName;
    }

    public void setIsoName(String isoName) {
        this.isoName = isoName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
