package com.kunal456k.moviedatabase.models;

import com.google.gson.annotations.SerializedName;

public class Languages {
    @SerializedName("iso_639_1")
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
