package com.kunal456k.moviedatabase.models;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("iso_3166_1")
    private String isoName;
    @SerializedName("name")
    private String name;

    public String getIsoName() {
        return isoName;
    }

    public String getName() {
        return name;
    }
}
